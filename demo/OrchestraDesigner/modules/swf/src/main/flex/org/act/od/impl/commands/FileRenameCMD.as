package org.act.od.impl.commands
{
	import mx.collections.ICollectionView;
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import org.act.od.framework.commands.AODCommand;
	import org.act.od.framework.control.OrDesignerEvent;
	import org.act.od.framework.view.ViewLocator;
	import org.act.od.impl.model.FigureEditorModel;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.view.EditorNavigatorChild;
	import org.act.od.impl.viewhelper.BpelEditorVH;
	import org.act.od.impl.viewhelper.FigureEditorVH;
	import org.act.od.impl.viewhelper.FileNavigatorViewVH;
	
	/**
	 * Rename a file.
	 */ 
	public class FileRenameCMD extends AODCommand
	{

		private var fileNavigatorViewVH :FileNavigatorViewVH = 
					ViewLocator.getInstance().getViewHelper(FileNavigatorViewVH.VH_KEY) as FileNavigatorViewVH;
		private var newName :String;
		private var oldName :String;
					
		private var figureEditorVH :FigureEditorVH;
		private var bpelEditorVH :BpelEditorVH;
					
		public function FileRenameCMD(){
			super();
		}
		/**
		 * 
		 * @param event {fileName}
		 * 
		 */
		override public function execute(event :OrDesignerEvent) :void{
			
			newName = event.data.fileName;
			oldName = fileNavigatorViewVH.getSelectedItem().@name;
			
			var newNameWithoutExtension :String;
			var currentName :String;
			var currentNameWithoutExtension :String;
			var path :String = fileNavigatorViewVH.getSelectedItemPath();
			
			var exist:Boolean = false;
			var xmlList :XMLList = XMLList(fileNavigatorViewVH.getParentItem(fileNavigatorViewVH.getSelectedItem())).elements();
			
			if(newName.indexOf(".", 0) != -1)
				newNameWithoutExtension = newName.substring(0, newName.indexOf(".", 0));
			else
				newNameWithoutExtension = newName;
			for(var i :int = 0; i < xmlList.length(); i++){
				currentName = xmlList[i].@name;
				if(currentName.indexOf(".", 0) != -1)
					currentNameWithoutExtension = currentName.substring(0, currentName.indexOf(".", 0));
				else
					currentNameWithoutExtension = currentName;
				if(newNameWithoutExtension == currentNameWithoutExtension)
					exist = true;
			}
			if(exist)
				Alert.show("This file name is exist.");
			else{
				fileNavigatorViewVH.reName(newName);
				
				var remote :RemoteObject = new RemoteObject();
				remote.destination = "navigator";
				remote.renameFile(path, newName);
				remote.addEventListener(FaultEvent.FAULT,fault);
			}
			this.Traversing(fileNavigatorViewVH.getSelectedItem());
		}

		private function Traversing(theParentItem:Object):void{
			var registration :Boolean;
			if(fileNavigatorViewVH.getDataDescriptor().isBranch(theParentItem)){
				var children :ICollectionView = fileNavigatorViewVH.getDataDescriptor().getChildren(theParentItem);
				var i:int;
				for(i = 0; i < children.length; i++){
					Traversing(children[i]);
				}
			}else if(theParentItem.@type == EditorNavigatorChild.FIGURE_EDITOR_TYPE)
			{
				registration = ViewLocator.getInstance().registrationExistsFor(FigureEditorVH.getViewHelperKey(theParentItem.@ID));
				if(registration){
					figureEditorVH = ViewLocator.getInstance().getViewHelper(FigureEditorVH.getViewHelperKey(theParentItem.@ID)) as FigureEditorVH;
					figureEditorVH.filePath = fileNavigatorViewVH.getSelectedItemPath();
				}
			}else if(theParentItem.@type == EditorNavigatorChild.BPEL_EDITOR_TYPE)
			{
				registration = ViewLocator.getInstance().registrationExistsFor(BpelEditorVH.getViewHelperKey(theParentItem.@ID));
				if(registration){
					bpelEditorVH = ViewLocator.getInstance().getViewHelper(BpelEditorVH.getViewHelperKey(theParentItem.@ID)) as BpelEditorVH;
					bpelEditorVH.filePath = fileNavigatorViewVH.getSelectedItemPath();
				}
				var grandparent :Object = fileNavigatorViewVH.getParentItem(theParentItem);
				var xmlList :XMLList = XML(grandparent).elements();
				for(var index :int = 0; index < xmlList.length(); index++){
					var message1 :String = XML(xmlList[index]).@name;
					var message2 :String = oldName.substring(0, newName.length - 5) + ".xml";
					if(XML(xmlList[index]).@name == oldName.substring(0, newName.length - 5) + ".xml")
						break;
				}
				var figureFileID :String = XML(xmlList[index]).@ID;
				var feModel :FigureEditorModel = OrDesignerModelLocator.getInstance().figureEditorNavigatorModel.getFigureEditorModel(figureFileID);
				if(feModel != null)
				feModel.relativeBpelID = null;
			}
		}
 		private function fault(event :FaultEvent):void{
        	Alert.show("Remote invoke error: "+event.message);
        } 
	}
}
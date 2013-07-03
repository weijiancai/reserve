package org.act.od.impl.commands
{
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import org.act.od.framework.commands.AODCommand;
	import org.act.od.framework.control.OrDesignerEvent;
	import org.act.od.framework.view.ViewLocator;
	import org.act.od.impl.model.FileNavigatorViewModel;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.viewhelper.FileNavigatorViewVH;
	/**
	 * Create a new project in fileNavigator.
	 */ 
	public class NewProjectCMD extends AODCommand
	{
		public function NewProjectCMD(){
			super();
		}
		/**
		 * @param event {projectName}
		 */
		override public function execute(event :OrDesignerEvent) :void{
			var fileNavigatorViewVH :FileNavigatorViewVH =
					ViewLocator.getInstance().getViewHelper(FileNavigatorViewVH.VH_KEY) as FileNavigatorViewVH;
			var fileNavigatorViewModel :FileNavigatorViewModel =
					OrDesignerModelLocator.getInstance().getFileNavigatorViewModel();

			var exist:Boolean = false;
			for(var i :int = 0; i < fileNavigatorViewModel.xmlList.length(); i++){
				if(event.data.projectName == fileNavigatorViewModel.xmlList[i].@name)
					exist = true;
			}
			if(exist)
				Alert.show("This project name is exist.");
			else{
				var remote :RemoteObject = new RemoteObject();
				remote.destination = "navigator";
				remote.createNewProject(event.data.projectName);
				remote.addEventListener(FaultEvent.FAULT,fault);
				
				var newProjectNode:XML = <folder/>;
				newProjectNode.@ID="0";
				newProjectNode.@name=event.data.projectName;
				newProjectNode.@type="project";
				fileNavigatorViewModel.xmlListCollection.addItem(newProjectNode);
   			}
		}
		
		private function fault(event :FaultEvent):void{
        	Alert.show("Remote invoke error: "+event.message);
        }
	}
}
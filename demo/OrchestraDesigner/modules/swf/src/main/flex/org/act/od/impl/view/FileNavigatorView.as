package org.act.od.impl.view
{
	import flash.events.ContextMenuEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.sendToURL;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuBuiltInItems;
	import flash.ui.ContextMenuItem;

	import mx.binding.utils.BindingUtils;
	import mx.collections.XMLListCollection;
	import mx.controls.Alert;
	import mx.controls.Tree;
	import mx.controls.listClasses.IListItemRenderer;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	import org.act.od.impl.model.FileNavigatorViewModel;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.viewhelper.FileNavigatorViewVH;

	/**
	 * The file navigator view.
	 */
	public class FileNavigatorView  extends Tree
	{
		/**
		 * The source for "project" image data binding.
		 */
		[Bindable]
		[Embed(source="/../assets/icon/accordion/project.gif")]
		private static var projectIcon:Class;

		/**
		 * The source for "folder" image data binding.
		 */
		[Bindable]
		[Embed(source="/../assets/icon/accordion/folder.gif")]
		private static var folderIcon:Class;

		/**
		 * The source for "file" image data binding.
		 */
		[Bindable]
        [Embed(source="/../assets/icon/accordion/file.gif")]
        private static var fileIcon :Class;

		/**
		 * The source for "bpel" image data binding.
		 */
        [Bindable]
        [Embed(source="/../assets/icon/accordion/bpel.gif")]
        private static var bpelIcon :Class;

		private var fileNavigatorViewVH :FileNavigatorViewVH;
		private var fileNavigatorViewModel :FileNavigatorViewModel;
		private var reNameItem :ContextMenuItem = new ContextMenuItem("Rename");
		private var downloadItem :ContextMenuItem = new ContextMenuItem("Download");

		/**
		 * Initialize the file navigator view.
		 */
		public function FileNavigatorView(){
			super();

			this.fileNavigatorViewVH = new FileNavigatorViewVH(this, FileNavigatorViewVH.VH_KEY);
			this.fileNavigatorViewModel = OrDesignerModelLocator.getInstance().getFileNavigatorViewModel();

			this.x=0;
			this.y=0;
			this.percentHeight=100;
			this.percentWidth=100;
			this.iconFunction=iconFun;
			this.doubleClickEnabled=true;

			this.showRoot = true;
			this.labelField="@name";
			this.dragEnabled = true;
			this.dropEnabled = false;

			var remote :RemoteObject = new RemoteObject();
			remote.destination = "navigator";
			remote.getXml();
            remote.addEventListener(ResultEvent.RESULT,getXmlResult);
            remote.addEventListener(FaultEvent.FAULT,fault);

			var contextMenu: ContextMenu = new ContextMenu();

			contextMenu.hideBuiltInItems();
            var defaultItems :ContextMenuBuiltInItems = contextMenu.builtInItems;
            defaultItems.print = false;

			contextMenu.customItems.push(reNameItem);
			contextMenu.customItems.push(downloadItem);
			this.contextMenu = contextMenu;

			this.initEventListener();
		}
		private function getXmlResult(event :ResultEvent):void{
			trace("---------------------------------- getXmlResult : "+event.result.valueOf());
			var str:String=event.result.valueOf();
        	var xml:XML=new XML(str);
        	var xmlList :XMLList = xml.elements();

        	this.fileNavigatorViewModel.xmlList = xmlList.copy();
        	this.fileNavigatorViewModel.xmlListCollection = new XMLListCollection(this.fileNavigatorViewModel.xmlList);

        	BindingUtils.bindProperty(this, "dataProvider" ,this.fileNavigatorViewModel, "xmlListCollection");
  		}
 		private function fault(event :FaultEvent):void{
        	Alert.show("Remote invoke error Houssem : "+event.message);
        }
		private function iconFun(item:Object):Class{
			var xml :XML = item as XML;
    		if(xml.@type == "project")
     			return projectIcon;
    		else if(xml.@type == "folder")
     			return folderIcon;
     		else if(xml.@type == EditorNavigatorChild.BPEL_EDITOR_TYPE)
     			return bpelIcon;
     		else
     			return fileIcon;
		}
		private function initEventListener():void{
			this.addEventListener(MouseEvent.DOUBLE_CLICK, fileNavigatorViewVH.tree_itemDoubleClick);
			this.addEventListener(KeyboardEvent.KEY_DOWN, fileNavigatorViewVH.itemDelete);
			this.contextMenu.addEventListener(ContextMenuEvent.MENU_SELECT,onRightClicked);
			this.reNameItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, fileNavigatorViewVH.fileRename);
			this.downloadItem.addEventListener(ContextMenuEvent.MENU_ITEM_SELECT, fileNavigatorViewVH.downloadBPEL);
		}
		private function onRightClicked(event: ContextMenuEvent):void{
			var rightClickItemRender:IListItemRenderer;
			var rightClickIndex:int;
   			if(event.mouseTarget is IListItemRenderer){
   				rightClickItemRender = IListItemRenderer(event.mouseTarget);
   			}else if(event.mouseTarget.parent is IListItemRenderer) {
   				rightClickItemRender = IListItemRenderer(event.mouseTarget.parent);
   			}
   			if(rightClickItemRender != null){
   				rightClickIndex = this.itemRendererToIndex(rightClickItemRender);
   				if(this.selectedIndex != rightClickIndex)
   					this.selectedIndex = rightClickIndex;
   			}
   			if(this.selectedItem.@type == EditorNavigatorChild.BPEL_EDITOR_TYPE){
   				downloadItem.visible = true;
   			}else{
   				downloadItem.visible = false;
   			}
		}
	}
}
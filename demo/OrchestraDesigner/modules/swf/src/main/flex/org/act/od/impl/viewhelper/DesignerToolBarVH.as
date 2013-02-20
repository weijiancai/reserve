package org.act.od.impl.viewhelper
{
	import flash.events.MouseEvent;

	import mx.events.CloseEvent;
	import mx.managers.PopUpManager;

	import org.act.od.framework.view.ViewHelper;
	import org.act.od.framework.view.ViewLocator;
//	import org.act.od.impl.events.DesignerToolBarAppEvent;
	import org.act.od.impl.events.FigureCanvasAppEvent;
//	import org.act.od.impl.events.FigureEditorAppEvent;
//	import org.act.od.impl.model.FigureEditorModel;
//	import org.act.od.impl.model.FileNavigatorViewModel;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.view.DesignerToolBar;
//	import org.act.od.impl.view.EditorNavigatorChild;
//	import org.act.od.impl.view.SaveFileWindow;
//	import org.act.od.impl.view.UDDIConfigurationWindow;

	/**
	 * The ViewHelper of DesignerToolBar.
	 * Used to isolate command classes from the implementation details of a view.
	 */
	public class DesignerToolBarVH extends ViewHelper
	{
		/**
		 * The key of DesignerToolBarVH
		 */
        public static const VH_KEY :String = "DesignerToolBarVH";
//        private var fileNavigatorModel:FileNavigatorViewModel;
		/**
		 * Initialize DesignerToolBar with DesignerToolBarVH
		 */
		public function DesignerToolBarVH(document : Object, id : String){
			super();
			initialized(document, id);
//			this.fileNavigatorModel=OrDesignerModelLocator.getInstance().getFileNavigatorViewModel();
		}
		/**
		 * Return DesignerToolBar.
		 */
		private function get designerToolBar() :DesignerToolBar{
			return this.view as DesignerToolBar;
		}
		/**
		 * The handler of "New Project" button.
		 */
		public function newProjectHandler(event:MouseEvent):void{
			/*var newProject:SaveFileWindow = SaveFileWindow(PopUpManager.createPopUp(OrDesignerModelLocator.getInstance().getOrchestraDesigner(), SaveFileWindow,true));
			newProject.setTitle("Project");
			PopUpManager.centerPopUp(newProject);
			newProject.addEventListener(CloseEvent.CLOSE, newProjectResult);*/
		}
		private function newProjectResult(event:CloseEvent):void{
			/*var projectName:String=SaveFileWindow(event.currentTarget).getText();
			if(projectName != "")
				new DesignerToolBarAppEvent(DesignerToolBarAppEvent.NEW_PROJECT,
					{projectName:projectName}).dispatch();*/
		}
		/**
		 * The handler of New Folder button.
		 */
		public function newFolderHandler(event:MouseEvent):void{
			/*var newFolder:SaveFileWindow = SaveFileWindow(PopUpManager.createPopUp(OrDesignerModelLocator.getInstance().getOrchestraDesigner(), SaveFileWindow,true));
			newFolder.setTitle("Folder");
			PopUpManager.centerPopUp(newFolder);
			newFolder.addEventListener(CloseEvent.CLOSE, newFolderResult);*/
		}
		private function newFolderResult(event:CloseEvent):void{
			/*var folderName:String=SaveFileWindow(event.currentTarget).getText();
			if(folderName != "")
				new DesignerToolBarAppEvent(DesignerToolBarAppEvent.NEW_FOLDER,
					{folderName:folderName}).dispatch();*/
		}
		/**
		 * The handler of "New File" button.
		 */
		public function newFileHandler(event:MouseEvent):void{
			/*var newFile:SaveFileWindow = SaveFileWindow(PopUpManager.createPopUp(OrDesignerModelLocator.getInstance().getOrchestraDesigner(), SaveFileWindow,true));
			newFile.setTitle("File");
			PopUpManager.centerPopUp(newFile);
			newFile.addEventListener(CloseEvent.CLOSE,newFileResult);*/
		}
		private function newFileResult(event:CloseEvent):void{
			/*var fileName:String = SaveFileWindow(event.currentTarget).getText();

			var fileType:String = SaveFileWindow(event.currentTarget).getSelectedFileType();

			if(fileName != ""){
				fileName = fileName + ".xml";
				new DesignerToolBarAppEvent(DesignerToolBarAppEvent.NEW_FILE,
					{fileName:fileName , fileType:fileType}).dispatch();
			}*/
		}
		/**
		 * The handler of "Save" button.
		 */
		public function onSaveHandler(event :MouseEvent):void {
			/*new DesignerToolBarAppEvent(DesignerToolBarAppEvent.FILE_SAVE,
				{}).dispatch();*/
		}
		/**
		 * The handler of "Save All" button.
		 */
		public function onSaveAllHandler(event :MouseEvent):void{
			/*new DesignerToolBarAppEvent(DesignerToolBarAppEvent.FILE_SAVEALL,
				{}).dispatch();*/
		}
		/**
		 * The handler of "Cut" button.
		 */
		public function onCutHandler(event:MouseEvent):void{
			/*var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
			if(activeFEModel != null){

				new FigureCanvasAppEvent(FigureCanvasAppEvent.FIGURES_COPY,
					{fileID :activeFEModel.fileID} ).dispatch();
				new FigureCanvasAppEvent(FigureCanvasAppEvent.FIGURE_DELETE_FROM_CANVAS,
					{fileID :activeFEModel.fileID} ).dispatch();
				new FigureCanvasAppEvent(FigureCanvasAppEvent.Canvas_Children_Changed,
					{canvas :null}).dispatch();
			}*/
		}
		/**
		 * The handler of "Copy" button.
		 */
		public function onCopyHandler(event:MouseEvent):void{
			/*var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
			if(activeFEModel != null){

				new FigureCanvasAppEvent(FigureCanvasAppEvent.FIGURES_COPY,
					{fileID :activeFEModel.fileID} ).dispatch();
			}*/
		}
		/**
		 * The handler of "Paste" button.
		 */
		public function onPasteHandler(event:MouseEvent):void{
			/*var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
			if(activeFEModel != null){

				new FigureCanvasAppEvent(FigureCanvasAppEvent.FIGURES_PASTE,
					{fileID :activeFEModel.fileID} ).dispatch();

				new FigureCanvasAppEvent(FigureCanvasAppEvent.Canvas_Children_Changed,
					{canvas :null}).dispatch();
			}*/
		}
		/**
		 * The handler of "Zoom In" button.
		 */
		public function onZoomInHandler(event:MouseEvent):void{
			/*var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
			if(activeFEModel != null){

				new FigureCanvasAppEvent(FigureCanvasAppEvent.Zoom_In,
					{fileID :activeFEModel.fileID} ).dispatch();
				new FigureCanvasAppEvent(FigureCanvasAppEvent.Canvas_Children_Changed,
					{canvas :null}).dispatch();
			}*/
		}
		/**
		 * The handler of "Zoom Out" button.
		 */
		public function onZoomOutHandler(event:MouseEvent):void{
			/*var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
			if(activeFEModel != null){

				new FigureCanvasAppEvent(FigureCanvasAppEvent.Zoom_Out,
					{fileID :activeFEModel.fileID} ).dispatch();
				new FigureCanvasAppEvent(FigureCanvasAppEvent.Canvas_Children_Changed,
					{canvas :null}).dispatch();
			}*/
		}
		/**
		 * The handler of "UDDI Configuration" button.
		 */
		 public function onDUUIConfigurationHandle(event:MouseEvent):void{
		 	/*var configuration :UDDIConfigurationWindow = UDDIConfigurationWindow(PopUpManager.createPopUp(OrDesignerModelLocator.getInstance().getOrchestraDesigner(),UDDIConfigurationWindow,true));
		 	PopUpManager.centerPopUp(configuration);
			configuration.addEventListener(CloseEvent.CLOSE, configurationResult);*/
		 }

		 private function configurationResult(event :CloseEvent):void{
		 	/*var _address :String = UDDIConfigurationWindow(event.currentTarget).getAddress();
		 	var _name :String = UDDIConfigurationWindow(event.currentTarget).getName();
		 	new DesignerToolBarAppEvent(DesignerToolBarAppEvent.UDDI_CONFIGURATION,
					{address :_address, name :_name} ).dispatch();*/
		 }

		/**
		* The handler of "Create BPEL" button.
		* This methode is called when a click in the button "Create BPEL" is done.
		* @param  event : Mouse clic Event on the "Create BPEL" button
		*
		*/
		public function onBpelHandler(event:MouseEvent):void{
//			var figureEditorNavigatorVH :EditorNavigatorVH = ViewLocator.getInstance().getViewHelper(EditorNavigatorVH.VH_KEY) as EditorNavigatorVH;
			/**
			* TODO:
			* Griser le bouton si ya pas de fichier de type "FIGURE_EDITOR_TYPE" ouvert
			*
			*/
			/*if(figureEditorNavigatorVH.getCurrentChildType() == null)
				return;
			if(figureEditorNavigatorVH.getCurrentChildType() == EditorNavigatorChild.FIGURE_EDITOR_TYPE){
				var activeFEModel :FigureEditorModel =  OrDesignerModelLocator.getInstance().getFigureEditorNavigatorModel().activeFigureEditorModel;
				var figureEditorVH :FigureEditorVH =
					ViewLocator.getInstance().getViewHelper(FigureEditorVH.getViewHelperKey(activeFEModel.fileID)) as FigureEditorVH;
				new FigureEditorAppEvent(FigureEditorAppEvent.BPEL_CREAT,
					{figureFilePath :figureEditorVH.filePath }).dispatch();
			}*/
		}
	}
}
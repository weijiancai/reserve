package org.act.od.impl.control{
	
	import org.act.od.framework.control.FrontController;
	import org.act.od.impl.commands.AboutInfoCMD;
//	import org.act.od.impl.commands.ActiveBpelEditorPageCMD;
//	import org.act.od.impl.commands.ActiveFigureEditorPageCMD;
//	import org.act.od.impl.commands.AttributeChangeCMD;
//	import org.act.od.impl.commands.AttributeViewUpdateCMD;
//	import org.act.od.impl.commands.BPELCreatCMD;
//	import org.act.od.impl.commands.BPELEditorCloseCMD;
//	import org.act.od.impl.commands.BPELFileOpenCMD;
//	import org.act.od.impl.commands.CancleCloseBPELEditorCMD;
//	import org.act.od.impl.commands.CancleCloseFigureEditorCMD;
//	import org.act.od.impl.commands.CanvasChildrenChangedCMD;
//	import org.act.od.impl.commands.ChangeFigureSizeCMD;
//	import org.act.od.impl.commands.ChangeToBPELViewCMD;
//	import org.act.od.impl.commands.CreateSubProcessCMD;
//	import org.act.od.impl.commands.FigureCopyFromCanvasCMD;
//	import org.act.od.impl.commands.FigureDeleteConfirmPopCMD;
//	import org.act.od.impl.commands.FigureDeleteFromCanvasCMD;
//	import org.act.od.impl.commands.FigureEditorCloseCMD;
//	import org.act.od.impl.commands.FigureFileOpenCMD;
//	import org.act.od.impl.commands.FigurePasteToCanvasCMD;
//	import org.act.od.impl.commands.FileDeleteCMD;
	import org.act.od.impl.commands.FileRenameCMD;
//	import org.act.od.impl.commands.FileSaveAllCMD;
//	import org.act.od.impl.commands.FileSaveCMD;
//	import org.act.od.impl.commands.FolderDeleteCMD;
//	import org.act.od.impl.commands.MoveDownCMD;
//	import org.act.od.impl.commands.MoveLeftCMD;
//	import org.act.od.impl.commands.MoveRightCMD;
//	import org.act.od.impl.commands.MoveUpCMD;
//	import org.act.od.impl.commands.NewBPELFileCMD;
//	import org.act.od.impl.commands.NewFileCMD;
//	import org.act.od.impl.commands.NewFolderCMD;
	import org.act.od.impl.commands.NewProjectCMD;
//	import org.act.od.impl.commands.NodeCreateLinkCMD;
//	import org.act.od.impl.commands.OpenSubProcessCMD;
//	import org.act.od.impl.commands.SelectAllCMD;
//	import org.act.od.impl.commands.SelectFigureOfToolPanelCMD;
//	import org.act.od.impl.commands.SetConnectionEndPointCMD;
//	import org.act.od.impl.commands.SetConnectionStartPointCMD;
//	import org.act.od.impl.commands.UDDIConfigurationCMD;
//	import org.act.od.impl.commands.UDDIRefFromServerCMD;
//	import org.act.od.impl.commands.ZoomInCMD;
//	import org.act.od.impl.commands.ZoomOutCMD;
//	import org.act.od.impl.events.AttributeViewAppEvent;
	import org.act.od.impl.events.DesignerMenuBarAppEvent;
//	import org.act.od.impl.events.DesignerToolBarAppEvent;
	import org.act.od.impl.events.FigureCanvasAppEvent;
//	import org.act.od.impl.events.FigureEditorAppEvent;
//	import org.act.od.impl.events.FigureEditorNavigatorAppEvent;
	import org.act.od.impl.events.FileNavigatorViewAppEvent;
//	import org.act.od.impl.events.ToolPanelAppEvent;
//	import org.act.od.impl.events.UDDIRefViewAppEvent;

	
	/**
     * A base class for an application specific front controller,
     * that is able to dispatch control following particular user gestures to appropriate
     * command classes.
	 */
	public class OrDesignerController extends FrontController{
		
		
		/**
		 * Registers all ICommand classes with the Front Controller, against an event name
		 * and listens for events with that name.
		 */

		public function OrDesignerController(){
					   
			//FigureEditorNavigatorAppEvent
//			addCommand(FigureEditorNavigatorAppEvent.FIGURE_EDITOR_CLOSE, FigureEditorCloseCMD);
					   
//			addCommand(FigureEditorNavigatorAppEvent.BPEL_EDITOR_CLOSE, BPELEditorCloseCMD);
					   
//			addCommand(FigureEditorNavigatorAppEvent.ACTIVE_FIGUREEDITOR_PAGE, ActiveFigureEditorPageCMD);
					   
//			addCommand(FigureEditorNavigatorAppEvent.ACTIVE_BPELEDITOR_PAGE, ActiveBpelEditorPageCMD);
			//added by zjn 09.7.28
//			addCommand(FigureEditorNavigatorAppEvent.CANCLE_CLOSE_FIGURE_EDITOR, CancleCloseFigureEditorCMD);
			
//			addCommand(FigureEditorNavigatorAppEvent.CANCLE_CLOSE_BPEL_EDITOR, CancleCloseBPELEditorCMD);
			
			//FigureEditorAppEvent
//			addCommand(FigureEditorAppEvent.CHANGE_To_XML_VIEW, ChangeToXMLViewCMD);
//			addCommand(FigureEditorAppEvent.CHANGE_To_XML_VIEW, ChangeToBPELViewCMD);
			
//			addCommand(FigureEditorAppEvent.BPEL_CREAT, BPELCreatCMD);
					   
			
			//AttributeView
//			addCommand(AttributeViewAppEvent.ATTRIBUTEVIEW_UPDATE, AttributeViewUpdateCMD);
			
//			addCommand(AttributeViewAppEvent.CHANGE_ATTRIBUTE, AttributeChangeCMD);
			
			//uddi
//			addCommand(UDDIRefViewAppEvent.GET_REF_FROM_SERVER, UDDIRefFromServerCMD);
			
			//tool panel
//			addCommand(ToolPanelAppEvent.SELECT_FIGURE, SelectFigureOfToolPanelCMD);
					   
			
			//figureCanvas
//			addCommand(FigureCanvasAppEvent.POP_FIGURE_DELETE_CONFIRM, FigureDeleteConfirmPopCMD);
			
//			addCommand(FigureCanvasAppEvent.FIGURE_DELETE_FROM_CANVAS, FigureDeleteFromCanvasCMD);
			
//			addCommand(FigureCanvasAppEvent.FIGURES_COPY, FigureCopyFromCanvasCMD);
			
//			addCommand(FigureCanvasAppEvent.FIGURES_PASTE, FigurePasteToCanvasCMD);
			
//			addCommand(FigureCanvasAppEvent.CHANGE_FIGURE_SIZE_IN_CANVAS, ChangeFigureSizeCMD);
			
//			addCommand(FigureCanvasAppEvent.CREATE_CONNECTION_START, SetConnectionStartPointCMD);
			
//			addCommand(FigureCanvasAppEvent.CREATE_CONNECTION_END, SetConnectionEndPointCMD);
			
//			addCommand(FigureCanvasAppEvent.Zoom_In, ZoomInCMD);
			
//			addCommand(FigureCanvasAppEvent.Zoom_Out, ZoomOutCMD);
			
			addCommand(FigureCanvasAppEvent.About_Info, AboutInfoCMD);
			
//			addCommand(FigureCanvasAppEvent.Select_All,SelectAllCMD);
			
//			addCommand(FigureCanvasAppEvent.Node_Create_Link,NodeCreateLinkCMD);
			
//			addCommand(FigureCanvasAppEvent.OPEN_SUBPROCESS,OpenSubProcessCMD);
			
//			addCommand(FigureCanvasAppEvent.CREATE_NEW_SUBPROCESS,CreateSubProcessCMD);
			
//			addCommand(FigureCanvasAppEvent.MOVE_LEFT,MoveLeftCMD);
			
//			addCommand(FigureCanvasAppEvent.MOVE_UP,MoveUpCMD);
			
//			addCommand(FigureCanvasAppEvent.MOVE_RIGHT,MoveRightCMD);
			
//			addCommand(FigureCanvasAppEvent.MOVE_DOWN,MoveDownCMD);
			
//			addCommand(FigureCanvasAppEvent.Canvas_Children_Changed,CanvasChildrenChangedCMD);
			
			
			//DesignerMenuBarAppEvent
			addCommand(DesignerMenuBarAppEvent.NEW_PROJECT, NewProjectCMD);
			
//			addCommand(DesignerMenuBarAppEvent.NEW_FOLDER, NewFolderCMD);
			
//			addCommand(DesignerMenuBarAppEvent.NEW_FILE, NewFileCMD);

//			addCommand(DesignerMenuBarAppEvent.FILE_SAVE, FileSaveCMD);
			
//			addCommand(DesignerMenuBarAppEvent.FILE_SAVEALL, FileSaveAllCMD);
			
//			addCommand(DesignerMenuBarAppEvent.UDDI_CONFIGURATION, UDDIConfigurationCMD);
			
			//DesignerToolBarAppEvent
//			addCommand(DesignerToolBarAppEvent.NEW_PROJECT, NewProjectCMD);
			
//			addCommand(DesignerToolBarAppEvent.NEW_FOLDER, NewFolderCMD);
			
//			addCommand(DesignerToolBarAppEvent.NEW_FILE, NewFileCMD);
			
//			addCommand(DesignerToolBarAppEvent.FILE_SAVE, FileSaveCMD);
			
//			addCommand(DesignerToolBarAppEvent.FILE_SAVEALL, FileSaveAllCMD);
			
//			addCommand(DesignerToolBarAppEvent.UDDI_CONFIGURATION, UDDIConfigurationCMD);
			
			//FileNavigatorViewAppEvent
//			addCommand(FileNavigatorViewAppEvent.FIGUREFILE_OPEN, FigureFileOpenCMD);
			
//			addCommand(FileNavigatorViewAppEvent.BPELFILE_OPEN, BPELFileOpenCMD);
			
//			addCommand(FileNavigatorViewAppEvent.NEW_BPEL_FILE, NewBPELFileCMD);
			
//			addCommand(FileNavigatorViewAppEvent.FILE_DELETE, FileDeleteCMD);
			
//			addCommand(FileNavigatorViewAppEvent.FOLDER_DELETE, FolderDeleteCMD);
			
			addCommand(FileNavigatorViewAppEvent.FILE_RENAME, FileRenameCMD);
	
		}
	
	}
	
}







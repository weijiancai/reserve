package org.act.od.impl.events
{
	import org.act.od.framework.control.OrDesignerEvent;
	/**
	 * This class define the events raised by the DesignerMenuBar.
	 */
	public class DesignerMenuBarAppEvent extends OrDesignerEvent
	{
		/**
		 * Create a new project.
		 */ 
		public static const NEW_PROJECT :String = "new_project_DesignerMenuBar";
		/**
		 * Create a new folder.
		 */ 
		public static const NEW_FOLDER :String = "new_folder_DesignerMenuBar";
		/**
		 * Create a new file.
		 */
		public static const NEW_FILE :String = "new_file_DesignerMenuBar";
		/**
		 * Close the selected file.
		 */ 
		public static const CLOSE :String = "close_DesignerMenuBar";
		/**
		 * Close all opening files.
		 */ 
		public static const CLOSEALL :String = "closeall_DesignerMenuBar";
		/**
		 * Save the selected file.
		 */ 
		public static const FILE_SAVE :String = "file_save_DesignerMenuBar";
		/**
		 * Save all opening files.
		 */ 
		public static const FILE_SAVEALL :String = "file_saveall_DesignerMenuBar";
		/**
		 * Select all figures in the canvas.
		 */ 
		public static const SELECTALLFIGURE :String = "selectallfigure_DesignerMenuBar";
		/**
		 * Create a new BPEL file.
		 */ 
		public static const CREATE_BPEL :String = "create_bpel_DesignerMenuBar";
		
		public static const UDDI_CONFIGURATION :String = "uddi_configuration_DesignerMenuBar";
		/**
		 * Show the "About Orchestra Designer" Window.
		 */ 
		public static const ABOUT_DESIGNER :String = "about_designer_DesignerMenuBar";
		/**
		 * Constructor, takes the event name (type) and data object (defaults to null)
		 * and also defaults the standard Flex event properties bubbles and cancelable
		 * to true and false respectively.
		 */ 
		public function DesignerMenuBarAppEvent(type : String, dataParameter:Object = null ,
											  bubbles : Boolean = false, cancelable : Boolean = false)
		{
			super(type, dataParameter, bubbles, cancelable);
		}

	}
}
package org.act.od.impl.events
{
	import org.act.od.framework.control.OrDesignerEvent;
	/**
	 * This class define the events raised by the FileNavigatorViewVH.
	 */
	public class FileNavigatorViewAppEvent extends OrDesignerEvent
	{	
		/**
		 * Open a figureFile.
		 */
		public static const FIGUREFILE_OPEN :String = "figurefile_open_FileNavigatorView";
		/**
		 * Open a BPELFile.
		 */
		public static const BPELFILE_OPEN :String = "bpelfile_open_FileNavigatorView";
		/**
		 * Delete a file.
		 */
		public static const FILE_DELETE :String = "file_delete_FileNavigatorView";
		/**
		 * Delete a folder or a project.
		 */
		public static const FOLDER_DELETE :String = "folder_delete_FileNavigatorView";
		/**
		 * Rename a file.
		 */
		public static const FILE_RENAME :String ="file_rename_FileNavigatorView";
		/**
		 * Create a new BPELFile.
		 */
		public static const NEW_BPEL_FILE :String = "new_bpel_FileNavigatorView";
		/**
		 * Constructor, takes the event name (type) and data object (defaults to null)
		 * and also defaults the standard Flex event properties bubbles and cancelable
		 * to true and false respectively.
		 */ 
		public function FileNavigatorViewAppEvent(type : String, dataParameter:Object = null ,
											  bubbles : Boolean = false, cancelable : Boolean = false)
		{
			super(type, dataParameter, bubbles, cancelable);
		}

	}
}
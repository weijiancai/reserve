package org.act.od.impl.viewhelper
{
	import org.act.od.framework.view.ViewHelper;
//	import org.act.od.impl.view.BpelEditor;
	
	/**
	* The ViewHelper of BPELEditor.
	* <p>Used to isolate command classes from the implementation details of a view.</p>
	* @author
	* @inheritDoc
	*/	
	public class BpelEditorVH extends ViewHelper{
					
		/**
		* Return the key of BPELEditorVH.
		* @param fileID file ID
		* @return key of this file
		* 
		*/	
		public static function getViewHelperKey(fileID :String) :String{
			return "VH:" + fileID;
		}
		
		/**
		* Initialize BpelEditor with BPELEditorVH.
		* @param document
		* @param id
		* 
		*/		
		public function BpelEditorVH(document : Object, id : String){
			super();
			initialized(document, id);
		}
		
		/**
		* Get BPELEditor.
		*/
		/*private function get bpelEditor() :BpelEditor{
			return this.view as BpelEditor;
		}*/
		
		/**
		* Get the filePath binding with this BPELEditorVH.
		*/
		/*public function get filePath():String{
			return this.bpelEditor.filePath;
		}*/
		/**
		 * Set the filePath binding with this BPELEditorVH.
		 */
		public function set filePath(filePath :String):void{
			/*this.bpelEditor.filePath = filePath;
			this.bpelEditor.label = filePath.substring(filePath.lastIndexOf("\\", filePath.length) + 1, filePath.length);*/
		}
		
		/**
		* Get the content of the BPELText in BPELEditor.
		* @return String Content
		* 
		*/
		/*public function getBpelContent():String{
			return this.bpelEditor.getBpelContent();
		}*/
	}
}
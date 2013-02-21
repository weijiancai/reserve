package org.act.od.impl.figure
{
	import mx.containers.Panel;

	/**
	 * The parent class of FigureEditor and BPELEditor.
	 * It content fileID binding with the file and the file's type.
	 */
	public class EditorNavigatorChild extends Panel{

		/**
		 * The flag of FigureEditor.
		 * It used to differentiate FigureEditor and BPELEditor.
		 */
		public static const FIGURE_EDITOR_TYPE :String = "Figure_Editor_Type";

		/**
		 * The flag of BPELEditor.
		 * It used to differentiate FigureEditor and BPELEditor.
		 */
		public static const BPEL_EDITOR_TYPE :String = "Bpel_Editor_Type";

		private var _type:String;

		private var _filePath :String;

		private var _fileID :String;

		public function EditorNavigatorChild( fileID:String, filePath:String ){
			super();
			this._filePath = filePath;
			this._fileID = fileID;
		}

		/**
		 * Return the type message.
		 */
		public function get type():String {
			return this._type;
		}
		/**
		 * Set the type message.
		 */
		public function set type(type:String):void {
			this._type = type;
		}
		/**
		 * return the filePath.
		 */
		public function get filePath():String{
			return this._filePath;
		}
		/**
		 * Set the filePath.
		 */
		public function set filePath( filePath:String):void{
			this._filePath = filePath;
		}
		/**
		 * Return fileID.
		 */
		public function get fileID() :String{
			return this._fileID;
		}

	}
}
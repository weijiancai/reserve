package org.act.od.impl.model
{
	import mx.collections.XMLListCollection;
	/**
	 * The model of FileNavigaor.
	 */
	public class FileNavigatorViewModel
	{
		/**
		 * The xmlList for the dataprovider of FileNavigator.
		 */
		private var _xmlList:XMLList;
		/**
		 * The xmlListCollection for the dataprovider of FileNavigator.
		 */
		private var _xmlListCollection :XMLListCollection;
		public function FileNavigatorViewModel(){
//			this._xmlList = new XMLList(
//				<folder ID="0" name="Examples" type="project">
//					<folder ID="0" name="examples" type="folder">
//						<file ID="File-9999" name="Example1.xml" type="Figure_Editor_Type"/>
//						<file ID="File-9998" name="Example2.xml" type="Figure_Editor_Type"/>
//					</folder>
//				</folder>);
//			this._xmlListCollection = new XMLListCollection(this._xmlList);
		}
  
		public function get xmlList():XMLList{
			return this._xmlList;
		}
		public function set xmlList(xmlList :XMLList) :void{
			this._xmlList = xmlList;
		}
		public function get xmlListCollection():XMLListCollection{
			return this._xmlListCollection;
		}
		public function set xmlListCollection(xmlListCollection :XMLListCollection) :void{
			this._xmlListCollection = xmlListCollection;
		}
	}
}
package org.act.od.impl.model{

	import flash.utils.Dictionary;

	/**
	 * Store message of primitives editor area
	 */
	public class FigureEditorNavigatorModel{

		/**
		 * The model of active figureEditor.
		 */
		private var _activeFEModel :FigureEditorModel;

		/**
		 * The model of active BPELEditor.
		 */
//		private var _activeBEModel :BpelEditorModel;

		/**
		 * A hash map, keyed on fileID with a FigureEditorModel as the value.
		 */
		private var figureEditorModelMap : Dictionary = new Dictionary();

		/**
		 * A hash map, keyed on fileID with a BPELEditorModel as the value.
		 */
		private var bpelEditorModelMap : Dictionary = new Dictionary();

		/**
		 * The xml form of the selected figure just for copy and paste operation.
		 */
		private var _xmlOfCopyFigures :XML;

		/**
		* Constructor,
		* <p>NONE</p>
		**/
		public function FigureEditorNavigatorModel(){

		}

		/**
		* Get the xml form of the selected figure just for copy and paste operation.
		*/
		public function get xmlOfCopyFigures():XML{
			return this._xmlOfCopyFigures;
		}
		/**
		* @private
		*/
		public function set xmlOfCopyFigures(xml :XML):void{
			this._xmlOfCopyFigures=xml;
		}
		/**
		* Get The model of active figureEditor
		*/
		public function get activeFigureEditorModel() :FigureEditorModel{
			return this._activeFEModel;
		}
		/**
		* @private
		*/
		/*public function set activeFigureEditorModel(figureEditorModel :FigureEditorModel) :void{
			this._activeFEModel = figureEditorModel;
		}*/

		/**
		* Get the model of active BPELEditor.
		*/
		/*public function get activeBpelEditorModel():BpelEditorModel{
			return this._activeBEModel;
		}*/

		/**
		* @private
		*/
		/*public function set activeBpelEditorModel(bpelEditorModel:BpelEditorModel):void{
			this._activeBEModel = bpelEditorModel;
		}*/

		/**
		* creat a new figureEditorModel with the parameter, if a model for the fileID
		* already exist, then just return the old model.
		* @param fileID ID of the Figure file
		* @param relativeBpelID ID of the Bpel file relatived with the figure file
		* @return FigureEditorModel
		*/
		/*public function addFigureEditorModel(fileID :String, relativeBpelID :String) :FigureEditorModel{
			var feModel :FigureEditorModel =  this.figureEditorModelMap[fileID];
			if(feModel == null) {
				feModel = new FigureEditorModel(fileID);
				feModel.relativeBpelID = relativeBpelID;
				this.figureEditorModelMap[fileID] = feModel;
   			}
 			return feModel;
		}*/
		/**
		* Delete the figureEditorModel corresponding to fileID parameter.
		* @param fileID ID file
		*/
		public function deleteFigureEditorModel(fileID :String) :void {
			delete this.figureEditorModelMap[fileID];
		}
		/**
		* Get figurEditorModel corresponding to fileID parameter.
		* @param fileID ID file
		* @return figurEditorModel.
		*/
		/*public function getFigureEditorModel(fileID :String) :FigureEditorModel {
			return this.figureEditorModelMap[fileID];
		}*/



		/**
		* creat a new bpelEditorModel with the parameter, if a model for the fileID
		* already exist, then just return the old model.
		*
		* @param fileID ID of the BPEL file.
		* @param relativeFigFileID ID of the Figure file relatived to BPEL file.
		* @return  bpelEditorModel.
		*/
 		/*public function addBpelEditorModel(fileID :String, relativeFigFileID :String) :BpelEditorModel{
 			var beModel :BpelEditorModel =  this.bpelEditorModelMap[fileID];
			if(beModel == null) {
				beModel = new BpelEditorModel(fileID, relativeFigFileID);
				this.bpelEditorModelMap[fileID] = beModel;

   			}
   			//add by likex 2009.12.3
//   			beModel.relativeFigureFileID = relativeFigFileID;
 			return beModel;
 		}*/

		/**
		* Delete the bpelEditorModel corresponding to fileID parameter.
		* @param fileID ID file
		*/
 		public function deleteBpelEditorModel(fileID :String) :void {
			delete this.bpelEditorModelMap[fileID];
		}

		/**
		* Get bpelEditorModel corresponding to fileID parameter.
		* @param fileID ID file
		* @return bpelEditorModel.
		*/
		/*public function getBpelEditorModel(fileID :String) :BpelEditorModel {
			return this.bpelEditorModelMap[fileID];
		}*/

	}
}
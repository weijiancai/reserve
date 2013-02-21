package org.act.od.impl.model{

	import mx.binding.utils.BindingUtils;

	import org.act.od.impl.figure.*;

	/**
	 * store primitives canvas
	 */
	public class FigureEditorModel
	{
		/**
		 * Bpmn process Type
		 */
		public static const BPMN_PROCESS_TYPE : String = "BPMN";

		/**
		 * Bpel process type
		 */
		public static const BPEL_PROCESS_TYPE : String = "BPEL";

		/**
		 * type of process (bpmn process or bpel process)
		 */
		[Bindable]
		public var processType:String;


		private var _rootFigure :IFigure;

		/** selected primitives */
		private var _selectedFigures :Array;

		private var _overFigure:IFigure;

		private var _canvasXML:XML = null;

		private var _backupCanvasXML : XML = null;

		private var _fileID :String;

		private var _relativeBpelFileID :String;

		[Bindable]
		public var _showingMultiple:Number=1;


		//store local id manager
		public var idManager :FigureIDManager = new FigureIDManager();


		public function FigureEditorModel(fileID :String){
			super();

			this._rootFigure = new ProcessFigure();

			this.processType = ProcessFigure(this._rootFigure).processType;

			this._selectedFigures = new Array();

			this._fileID = fileID;
			BindingUtils.bindSetter(this.smChange,this,"_showingMultiple");

			BindingUtils.bindProperty(this,"processType",this._rootFigure,"processType");
		}

		//added by zjn
		public function isChanged() : Boolean {
			var isChanged : Boolean = true;

			if(this._canvasXML.toXMLString().localeCompare("<Process/>") == 0) {
				isChanged = false;
			}
			else if(this._backupCanvasXML != null) {
				var backupCanvasXMLString : String = this._backupCanvasXML.toXMLString();
				var currentCanvasXMLString : String = this._canvasXML.toXMLString();
				if(backupCanvasXMLString.localeCompare(currentCanvasXMLString) == 0) {
					isChanged = false;
				}
			}

			return isChanged;
		}

		//added by zjn 09.7.28
		public function updateCanvasXML():void{
			this._canvasXML = _rootFigure.outputAllInformation();
		}

		//added by zjn 09.7.28
		public function saveCanvasXML() : void {
			updateCanvasXML();
			this._backupCanvasXML = this._canvasXML.copy();
		}

		//added by zjn 09.7.28
		public function unsaveCanvasXML() : void {
			if(this._backupCanvasXML == null)
			{
				this._canvasXML = <Process/>;
				this._canvasXML.@processType=this.processType;
			}
			else
				this._canvasXML = this._backupCanvasXML.copy();
		}

		public function get canvasXML() :XML {
			return this._canvasXML;
		}


		public function get fileID():String{
			return this._fileID;
		}

		public function get relativeBpelID():String{
			return this._relativeBpelFileID;
		}

		public function set relativeBpelID(bpelID :String) :void{
			this._relativeBpelFileID = bpelID;
		}

		public function set showingMultiple(sm:Number):void{
			_showingMultiple=sm;
		}

		public function get showingMultiple():Number{
			return _showingMultiple;
		}

		/**
		 * clear elements of selectedfigure
		 * redraw selected elements
		 */
		public function resetSelectedFigure() :void{
			for(var i:int=0; i<_selectedFigures.length; i++){
				IFigure(_selectedFigures[i]).setSelected(false);
				IFigure(_selectedFigures[i]).setIsShowContextPanel(false);
				IFigure(_selectedFigures[i]).updateDraw();
			}
			_selectedFigures.splice(0, _selectedFigures.length);
		}


		/**
		 * clear elements of selectedfigure not redraw selected elements
		 */
		public function clearSelectedFigure() :void{
			for(var i:int=0; i<_selectedFigures.length; i++){
				IFigure(_selectedFigures[i]).setSelected(false);
			}
			_selectedFigures.splice(0, _selectedFigures.length);
		}


		public function get rootFigure() :IFigure{
			return this._rootFigure;
		}

		public function get selectedFigures() :Array{
			return this._selectedFigures;
		}

		public function get overFigure():IFigure{
			return this._overFigure;
		}

		public function set overFigure(ifi:IFigure):void{
			this._overFigure=ifi;
		}
		protected function smChange(sm:Number):void{
			var ar:Array=new Array();
			var con:Array=new Array();
			if(this.rootFigure is ProcessFigure){
				ProcessFigure(this.rootFigure).multiple=this.showingMultiple;
			}
			this.rootFigure.ifiguretoarray(ar);
			var i:int;
			/*for(i=0;i<ar.length;i++){
				IFigure(ar[i]).setMultiple(this.showingMultiple);
				if(ar[i] is ConnectionFigure){
					con.push(ar[i]);
				}
			}
			for(i=0;i<con.length;i++){
				ConnectionFigure(con[i]).autoSetAnchor();
			}
			for(i=0;i<ar.length;i++){
				IFigure(ar[i]).updateDraw();
			}*/
		}

	}
}
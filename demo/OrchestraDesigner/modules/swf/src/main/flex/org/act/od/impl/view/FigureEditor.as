package org.act.od.impl.view{
    import org.act.od.impl.figure.*;

	import mx.binding.utils.BindingUtils;
	import mx.containers.HBox;
	import mx.containers.HDividedBox;
	import mx.containers.Panel;
	import mx.containers.TabNavigator;
	import mx.containers.ViewStack;
	import mx.controls.LinkBar;
	import mx.controls.TextArea;
	import mx.events.DividerEvent;
	import mx.events.FlexEvent;
	import mx.events.ItemClickEvent;

	import org.act.od.impl.figure.AbstractFigure;
//	import org.act.od.impl.figure.ConnectionFigure;
	import org.act.od.impl.figure.IFigure;
	import org.act.od.impl.figure.ProcessFigure;
	import org.act.od.impl.model.*;
	import org.act.od.impl.other.Localizator;
//	import org.act.od.impl.view.bpmn.BpmnToolPanel;
//	import org.act.od.impl.view.bpmn.TabBpmnBpel;
	import org.act.od.impl.viewhelper.FigureEditorVH;

	/**
	 * The Editor for figure file.
	 */
	public class FigureEditor extends EditorNavigatorChild{

		private var linkBar :LinkBar;

		private var bpmlLinkBar :LinkBar;

		private var viewStack :ViewStack;

		private var bpmnViewStack :ViewStack;

		private var _graphicalView :HDividedBox;

		private var _bpmnGraphicalView :HDividedBox;

//		private var _figureCanvas :FigureCanvas;

//		private var _bpmnFigureCanvas :FigureCanvas;

//		private var _toolPanel :ToolPanel;

//		private var _bpmnToolPanel :BpmnToolPanel;

		private var _xmlText :TextArea;

		private var _bpelText : TextArea;

		//model attribute
		private var _figureEditorModel :FigureEditorModel = null;

		private var _bpmnFigureEditorModel :FigureEditorModel = null;

		//view helper
		private var figureEditorVH :FigureEditorVH;

		private var localizator :Localizator;

//		private var  _bpmnBpelTab : TabBpmnBpel;

		/**
		 * Create a new FigureEditor by filePath, fileName and feModel.
		 */
		public function FigureEditor(filePath :String, fileName :String, feModel :FigureEditorModel)
		{
			super(feModel.fileID, filePath);

			//component view init
			this.percentWidth = 100;
			this.percentHeight = 100;

			this.type = EditorNavigatorChild.FIGURE_EDITOR_TYPE;
			this.label = fileName;
			this._figureEditorModel = feModel;
			this._bpmnFigureEditorModel = feModel;

			//this._bpmnFigureEditorModel = feModel;


			//view helper
			figureEditorVH = new FigureEditorVH( this, FigureEditorVH.getViewHelperKey(feModel.fileID) );
			this.localizator = Localizator.getInstance();

			viewStack = new ViewStack();
			viewStack.percentHeight = 100;
			viewStack.percentWidth = 100;

			bpmnViewStack = new ViewStack();
			bpmnViewStack.percentHeight = 100;
			bpmnViewStack.percentWidth = 100;

//			_figureCanvas = new FigureCanvas(_figureEditorModel);

//			_bpmnFigureCanvas = new FigureCanvas(_bpmnFigureEditorModel);

			_graphicalView = new HDividedBox();
			_graphicalView.percentHeight = 95;
			_graphicalView.percentWidth = 100;
			_graphicalView.setStyle("borderStyle", "solid");
			_graphicalView.label = localizator.getText('figureeditor.graphic');
			_graphicalView.addEventListener(DividerEvent.DIVIDER_RELEASE, figureEditorVH.dividerReleaseHandle);

			_bpmnGraphicalView = new HDividedBox();
			_bpmnGraphicalView.percentHeight = 95;
			_bpmnGraphicalView.percentWidth = 100;
			_bpmnGraphicalView.setStyle("borderStyle", "solid");
			_bpmnGraphicalView.label = localizator.getText('figureeditor.graphic');
			_bpmnGraphicalView.addEventListener(DividerEvent.DIVIDER_RELEASE, figureEditorVH.dividerReleaseHandle);

//			_figureCanvas.percentHeight = 95;
//			_figureCanvas.percentWidth = 100;
//			_figureCanvas.label= localizator.getText('figureeditor.graphic');

//			_bpmnFigureCanvas.percentHeight = 95;
//			_bpmnFigureCanvas.percentWidth = 100;
//			_bpmnFigureCanvas.label= localizator.getText('figureeditor.graphic');

//			_graphicalView.addChild(_figureCanvas);

//			_bpmnGraphicalView.addChild(_bpmnFigureCanvas);

//			_toolPanel = new ToolPanel();

			//bpmn palette
//			_bpmnToolPanel = new BpmnToolPanel();

			// bpmn toolPanel layout
			var bpmnToolPanelTabNav :TabNavigator = new TabNavigator();
			bpmnToolPanelTabNav.percentWidth = 20;
			bpmnToolPanelTabNav.percentHeight = 100;
//			bpmnToolPanelTabNav.addChild(_bpmnToolPanel);

			//toolPanel layout
			var toolPanelTabNav :TabNavigator = new TabNavigator();
			toolPanelTabNav.percentWidth = 20;
			toolPanelTabNav.percentHeight = 100;
//			toolPanelTabNav.addChild(_toolPanel);

			_graphicalView.addChild(toolPanelTabNav);

			_bpmnGraphicalView.addChild(bpmnToolPanelTabNav);

			var xmlHBox :HBox = new HBox();
			xmlHBox.percentWidth = 100;
			xmlHBox.percentHeight = 90;
			xmlHBox.label =  localizator.getText('figureeditor.bpel');//modified from "XML" to "BPEL"
			xmlHBox.percentHeight = 100;
			xmlHBox.percentWidth = 100;

			_bpelText = new TextArea();
			_bpelText.percentWidth = 100;
			_bpelText.percentHeight = 100;
			_bpelText.editable = false;
			BindingUtils.bindSetter(setXMLContent, _bpelText,"text");
			xmlHBox.addChild(_bpelText);

			viewStack.addChild(_graphicalView);
			viewStack.addChild(xmlHBox);
			viewStack.percentHeight = 100;
			viewStack.percentWidth = 100;

			bpmnViewStack.addChild(_bpmnGraphicalView);
			//bpmnViewStack.addChild(xmlHBox);
			bpmnViewStack.percentHeight = 100;
			bpmnViewStack.percentWidth = 100;

			linkBar = new LinkBar();
			linkBar.dataProvider = viewStack;
			linkBar.percentHeight = 100;
			linkBar.percentWidth = 40;
			linkBar.addEventListener(ItemClickEvent.ITEM_CLICK, figureEditorVH.onItemClickHandle);

			bpmlLinkBar = new LinkBar();
			bpmlLinkBar.dataProvider = bpmnViewStack;
			bpmlLinkBar.percentHeight = 100;
			bpmlLinkBar.percentWidth = 40;
			bpmlLinkBar.addEventListener(ItemClickEvent.ITEM_CLICK, figureEditorVH.onItemClickHandle);

			var bpmnBox:HBox = new HBox;
			bpmnBox.percentWidth = 100;
			bpmnBox.percentHeight = 5;
			bpmnBox.addChild(bpmlLinkBar);

			var bpelBox:HBox = new HBox;
			bpelBox.percentWidth = 100;
			bpelBox.percentHeight = 5;
			bpelBox.addChild(linkBar);

//			_bpmnBpelTab = new TabBpmnBpel();

			var bpelCanvas : Panel;

			var bpmnCanvas : Panel;

//			_bpmnBpelTab.percentHeight = 100;
//			_bpmnBpelTab.percentWidth = 100;

			if(feModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
//				bpelCanvas = _bpmnBpelTab.createTab("BPEL");
				bpelCanvas.addChild(viewStack);
				bpelCanvas.addChild(bpelBox);

//				this.loadGraphFile(this._figureCanvas, this._figureEditorModel);
			}
			else
				if(feModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
				{
//					bpmnCanvas = _bpmnBpelTab.createTab("BPMN");
					bpmnCanvas.addChild(bpmnViewStack);
					bpmnCanvas.addChild(bpmnBox);

//					this.loadGraphFile(this._bpmnFigureCanvas, this._bpmnFigureEditorModel);
				}


//			this.addChild(_bpmnBpelTab);

			this.setStyle("headerHeight","5");

			if(feModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
//				this.loadGraphFile(this._figureCanvas, this._figureEditorModel);
			}
			else
				if(feModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
				{
//					this.loadGraphFile(this._bpmnFigureCanvas, this._bpmnFigureEditorModel);
				}

		}

		/**
		 * Set figureEditorModel.
		 */
		public function set figureEditorModel(feModel:FigureEditorModel):void {
			this._figureEditorModel = feModel;
//			this._figureCanvas.setFigureEditorModel(feModel);
		}
		/**
		 * Return figureEditorModel
		 */
		public function get figureEditorModel():FigureEditorModel {
			return this._figureEditorModel;
		}

		/**
		 * Set figureEditorModel.
		 */
		public function set bpmnFigureEditorModel(feModel:FigureEditorModel):void {
			this._bpmnFigureEditorModel = feModel;
//			this._bpmnFigureCanvas.setFigureEditorModel(feModel);
		}

		/**
		 * Return figureEditorModel
		 */
		public function get bpmnFigureEditorModel():FigureEditorModel {
			return this._bpmnFigureEditorModel;
		}

		/**
		 * return figureCanvas.
		 */
		/*public function get bpmnBpelTab() :TabBpmnBpel{
			return this._bpmnBpelTab;
		}*/

		/**
		 * return figureCanvas.
		 */
		/*public function set bpmnBpelTab(bpmnBpelTab : TabBpmnBpel) :void{
			this._bpmnBpelTab = bpmnBpelTab;
		}*/

		/**
		 * return figureCanvas.
		 */
		/*public function get figureCanvas() :FigureCanvas{
			return this._figureCanvas;
		}*/

		/**
		 * return bpmnFigureCanvas.
		 */
		/*public function get bpmnFigureCanvas() :FigureCanvas{
			return this._bpmnFigureCanvas;
		}*/

		/**
		 * return bpmnFigureCanvas.
		 */
		/*public function set bpmnFigureCanvas(figureCanvas : FigureCanvas) :void{
			this._bpmnFigureCanvas = figureCanvas;
		}*/


		/**
		 * Return toolPanel.
		 */
		/*public function get toolPanel():ToolPanel{
			return this._toolPanel;
		}*/
		/**
		 * Return bpmntoolPanel.
		 */
		/*public function get bpmnToolPanel():BpmnToolPanel{
			return this._bpmnToolPanel;
		}*/
		/**
		 * Set the content of bpelText.
		 */
		public function setXMLContent(xmlString :String) :void {
			this._bpelText.htmlText = xmlString;
		}
		/**
		 * return the content of bpelText.
		 */
		public function getXMLContent() :String {
			return this._xmlText.text;
		}


		/**
		 * Initialize the canvas by a graphFile.
		 * @param figureCanvas
		 * @param figureEditorModel
		 *
		 */
		/*public function loadGraphFile(figureCanvas : FigureCanvas, figureEditorModel : FigureEditorModel) :void
		{
			figureCanvas.updateFigure();

			if( figureEditorModel.canvasXML != null ){

				figureEditorModel.resetSelectedFigure();

				var rootFig :ProcessFigure = ProcessFigure(figureEditorModel.rootFigure);

				rootFig.readInformationToFigure(figureEditorModel.canvasXML);

				//to draw the figures
				var figArray :Array = new Array();
				rootFig.ifiguretoarray(figArray);

				var fig:IFigure;

				var connection :Array = new Array();

				for(var i:int=0; i<figArray.length; i++){

					fig = figArray[i] as IFigure;

					if(fig is ConnectionFigure){
						connection.push(fig);
					}
					AbstractFigure(fig).addEventListener(FlexEvent.CREATION_COMPLETE,CreationComplete);
					figureCanvas.addChild(AbstractFigure(fig));
				}
				figureEditorModel._showingMultiple=1;

				for(i=0; i<connection.length; i++){
					ConnectionFigure(connection[i]).autoSetAnchor();
				}
			}

		}*/

		private function CreationComplete(event:FlexEvent):void{
			AbstractFigure(event.currentTarget).updateDraw();
			AbstractFigure(event.currentTarget).removeEventListener(FlexEvent.CREATION_COMPLETE,CreationComplete);
		}
	}
}
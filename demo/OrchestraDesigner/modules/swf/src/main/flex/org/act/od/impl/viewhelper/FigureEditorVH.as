package org.act.od.impl.viewhelper
{
	import flash.display.DisplayObject;

	import mx.containers.HDividedBox;
	import mx.controls.Alert;
	import mx.events.DividerEvent;
	import mx.events.ItemClickEvent;

	import org.act.od.framework.view.ViewHelper;
//	import org.act.od.impl.events.FigureEditorAppEvent;
	import org.act.od.impl.model.FigureEditorModel;
	import org.act.od.impl.view.FigureEditor;

	/**
	 * The ViewHelper of FigureEditor.
	 * Used to isolate command classes from the implementation details of a view.
	 */
	public class FigureEditorVH extends ViewHelper
	{

		/**
		 * Return the key of FigureEditorVH.
		 */
		public static function getViewHelperKey(fileID:String):String
		{
			return "VH:" + fileID;
		}

		/**
		 * Initialize FigureEditor with FigureEditorVH.
		 */
		public function FigureEditorVH(document:Object, id:String)
		{
			super();
			initialized(document, id);
		}

		/**
		 * Return FigureEditor
		 */
		public function get figureEditor():FigureEditor
		{
			return this.view as FigureEditor;
		}

		/**
		 * Return the filePath binding with this BPELEditorVH.
		 */
		/*public function get filePath():String
		{
			return this.figureEditor.filePath;
		}*/

		/**
		 * Set the filePath binding with this BPELEditorVH.
		 */
		public function set filePath(filePath:String):void
		{
//			this.figureEditor.filePath=filePath;
//			this.figureEditor.label=filePath.substring(filePath.lastIndexOf("\\", filePath.length) + 1, filePath.length);
		}

		/**
		 * Set the content of bpelText in figureEditor.
		 */
		public function setFEXMLContent(xmlString:String):void
		{
//			this.figureEditor.setXMLContent(xmlString);
		}

		/**
		 * update the canvas
		 */
		public function updateFigureCanvas():void
		{

			/*if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
				this.figureEditor.figureCanvas.updateFigure();
			}
			else if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
			{
				this.figureEditor.bpmnFigureCanvas.updateFigure();
			}*/
		}

		public function loadGraphFile():void
		{
			/*if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
				this.figureEditor.loadGraphFile(this.figureEditor.figureCanvas,this.figureEditor.figureEditorModel);
			}
			else if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
			{
				this.figureEditor.loadGraphFile(this.figureEditor.bpmnFigureCanvas,this.figureEditor.bpmnFigureEditorModel);
			}*/
		}

		/**
		 * Remove figures form the canvas.
		 */
		public function removeFiguresFromCanvas(figure:DisplayObject):void
		{
			/*if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
				if (this.figureEditor.figureCanvas.contains(figure))
				{
					this.figureEditor.figureCanvas.removeChild(figure);
				}
			}
			else if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
			{
				if (this.figureEditor.bpmnFigureCanvas.contains(figure))
				{
					this.figureEditor.bpmnFigureCanvas.removeChild(figure);
				}
			}*/
		}

		/**
		 * add figures to canvas.
		 */
		public function addFigureToCanvas(figure:DisplayObject):void
		{

				/*if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
				{
					if(!this.figureEditor.figureCanvas.contains(figure))
					{
						//Alert.show("contains : "+this.figureEditor.figureCanvas.contains(figure));
						this.figureEditor.figureCanvas.addChild(figure);
					}

				}
				else
					if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
					{
						if(!this.figureEditor.bpmnFigureCanvas.contains(figure))
							this.figureEditor.bpmnFigureCanvas.addChild(figure);
					}*/

		}

		/**
		 * Handler of BPEL view
		 * @param event
		 */
		public function onItemClickHandle(event:ItemClickEvent):void
		{
			var bnLabel:String=event.label;

			if (bnLabel == "BPEL")
			{
//				new FigureEditorAppEvent(FigureEditorAppEvent.CHANGE_To_XML_VIEW, {figureEditor: this.view}).dispatch();
			}
		}

		/**
		 * SystemEvent handle
		 * @param event
		 */
		public function dividerReleaseHandle(event:DividerEvent):void
		{
			/*var newWidth:Number=HDividedBox(event.target).getDividerAt(0).x;
			if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
			{
				this.figureEditor.figureCanvas.changeWidth(newWidth);
			}
			else if (this.figureEditor.figureEditorModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
			{
				this.figureEditor.bpmnFigureCanvas.changeWidth(newWidth);
			}*/

		}

	}
}


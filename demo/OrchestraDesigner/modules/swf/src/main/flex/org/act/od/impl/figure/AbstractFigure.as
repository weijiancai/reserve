package org.act.od.impl.figure
{
//	import editor.GraphicalViewer;

	import flash.display.DisplayObjectContainer;
	import flash.display.Sprite;

	import mx.binding.utils.BindingUtils;
	import mx.collections.ArrayCollection;
	import mx.containers.Canvas;
	import mx.core.UIComponent;

	import org.act.od.framework.view.ViewLocator;
	import org.act.od.impl.figure.bpmn.BpmnFigureFactory;
	import org.act.od.impl.model.FigureEditorModel;
	import org.act.od.impl.model.OrDesignerModelLocator;
	import org.act.od.impl.viewhelper.FigureEditorVH;
	import org.act.od.impl.vo.BasicAttribute;
	import org.act.od.impl.vo.IAttribute;

	public class AbstractFigure extends UIComponent implements IFigure
	{
		protected var sprt:Sprite;

		protected var rx:Number; //right x coordinate
		protected var ry:Number; //right y coordinate
		protected var standardwidth:Number; //default width
		protected var standardheight:Number; //default height

		[Bindable]
		public var drawid:Number;
		protected var parentNode:IFigure;
		protected var selectedState:Boolean; //whether selected flag


		[Bindable]
		public var multiple:Number = 1;
		protected var premultiple:Number = 1;


		[Bindable]
		public var ID:int;
		[Bindable]
		public var figureName:String;

		public var attribute:IAttribute; //added by zjn, temporarily public

		protected var defaultLineThickness:Number = 2;
		protected var defaultSelectedLineThickness:Number = 6;
		protected var defaultSelectedCircleRadius:Number = 3;
		protected var selectedCircleRadius:Number = defaultSelectedCircleRadius;
		protected var lineThickness:Number = defaultLineThickness;
		protected var selectedLineThickness:Number = defaultSelectedLineThickness;

		protected var defaultFontSize:Number = 10;
		protected var fontsize:Number = defaultFontSize;

		private var processType:String;

		public function AbstractFigure(processType:String=null)
		{
			this.processType = processType;

			super();
			sprt = new Sprite();
			selectedState = false;
			this.addChild( sprt ); //newly added on 2009.6.22 11:43

			BindingUtils.bindSetter( this.OutputScale, this, "multiple" );

			//added by zjn
			attribute = new BasicAttribute();

			if(processType!=null)
			{
				if(processType==FigureEditorModel.BPEL_PROCESS_TYPE)
					BasicAttribute( attribute ).type = FigureFactory.id2name( this.drawid );
				else
					if(processType==FigureEditorModel.BPMN_PROCESS_TYPE)
						BasicAttribute( attribute ).type = BpmnFigureFactory.id2name( this.drawid );
			}
			else
				BasicAttribute( attribute ).type = FigureFactory.id2name( this.drawid );


			//added by zjn
			//bind attribute value and its property member
			BindingUtils.bindProperty( this, "figureName", this.attribute, "name" );
			BindingUtils.bindProperty( this, "ID", this.attribute, "id" );
			BindingUtils.bindSetter( this.setAttributeType, this, "drawid" );
			BindingUtils.bindSetter( this.updateAttributeName, this, "figureName" );

		}


		//added by zjn
		protected function setAttributeType( drawid:Number ):void
		{
			var type:String;

			if(this.processType != null)
			{
				if(this.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
					type = FigureFactory.id2name( drawid );
				else
					if(this.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
						type = BpmnFigureFactory.id2name( drawid );
			}
			else
				type = FigureFactory.id2name( drawid );

			BasicAttribute( attribute ).type = type;
		}

		//added by zjn
		protected function updateAttributeName( name:String ):void
		{
			//update name attribute
			var atts:ArrayCollection = new ArrayCollection();
			atts.addItem({ name: "Name: ", value: figureName });
			this.attribute.update( atts );
		}

		public function getIsShowContextPanel():Boolean
		{
			return false;
		}

		public function setIsShowContextPanel( isShowContextPanel:Boolean ):void
		{

		}

		public function setID( ID:int ):void
		{
			this.ID = ID;
			//added by zjn
			BasicAttribute( attribute ).id = this.ID;
		}

		public function getID():int
		{
			return this.ID;
		}

		public function setxy( x:Number, y:Number ):void
		{
			this.setposition( x, y );
		}

		public function getx():Number
		{
			return x;
		}

		public function gety():Number
		{
			return y;
		}

		public function getstandardwidth():Number
		{
			return standardwidth;
		}

		public function getstandardheight():Number
		{
			return standardheight;
		}

		public function drawclear():void
		{
			this.graphics.clear();
			sprt.graphics.clear();
		}

		public function getdrawid():Number
		{
			return drawid;
		}

		public function getrx():Number
		{
			return rx;
		}

		public function getry():Number
		{
			return ry;
		}

		public function getchildren():Array
		{
			return null;
		}

		public function addchild( child:IFigure ):void
		{

		}

		public function addchildWithConnection( child:IFigure ):void
		{

		}

		public function removechildWithoutConnection( child:IFigure ):void
		{

		}

		public function removechildWithConnection( child:IFigure, headarr:Array, tailarr:Array ):void
		{

		}

		public function haschild( child:IFigure ):Boolean
		{
			return false;
		}

		public function searchchildWithId( Id:String ):IFigure
		{
			var ifi:IFigure = null;

			return ifi;
		}

		public function getparent():IFigure
		{
			return parentNode;
		}

		public function getrootfigure():IFigure
		{
			var ifi:IFigure = this;
			while ( ifi.getparent())
			{
				ifi = ifi.getparent();
			}
			return ifi;
		}

		public function setparent( parent:IFigure ):void
		{
			this.parentNode = parent;
		}

		public function getupperfigure( x:Number, y:Number, flag:int = -1 ):IFigure
		{
			var temp:IFigure = null;
			var ret:IFigure = null;
			var i:int;
			if ( this.getchildren())
			{
				for ( i = 0; i < this.getchildren().length; i++ )
				{
					temp = IFigure( this.getchildren()[ i ]).getupperfigure( x, y, flag );
					if ( temp != null )
					{
						if ( flag == -1 )
						{
							if ( ret )
							{
								if ( ret.isin( x, y ) < temp.isin( x, y ))
								{
									ret = temp;
								}
							}
							else
							{
								ret = temp;
							}
						}
						else
						{
							ret = temp;
							break;
						}
					}
				}
			}
			if ( flag == -1 )
			{
				if ( isin( x, y ))
				{
					if ( ret )
					{
						if ( ret.isin( x, y ) < isin( x, y ))
						{
							ret = temp;
						}
					}
					else
					{
						ret = this;
					}
				}
			}
			else
			{
				if ( !ret )
				{
					if ( isin( x, y ) == flag )
					{
						ret = this;
					}
				}
			}
			return ret;
		}

		public function ifiguretoarray( ar:Array ):void
		{
			var i:int;
			var ifi:IFigure;

			if ( ar.indexOf( this ) == -1 )
			{
//				this.setSelected(true);
				ar.unshift( this );
			}
		}

		public function getdrawx():Number
		{
			return getx();
		}

		public function getdrawy():Number
		{
			return gety();
		}

		public function isSelected():Boolean
		{
			return selectedState;
		}

		public function setSelected( isslct:Boolean ):void
		{
			selectedState = isslct;
		}

		public function drawSelectedStyle():void
		{
			sprt.graphics.lineStyle( this.defaultSelectedLineThickness * this.multiple, 0x2244ff, 0.4 );
			sprt.graphics.drawRoundRect( 0, 0, this.width, this.height, 3, 3 );

			sprt.graphics.lineStyle( 2, 0x000000, 1 );
			sprt.graphics.drawCircle( 0, 0, this.selectedCircleRadius );
			sprt.graphics.drawCircle( 0, this.height / 2, this.selectedCircleRadius );
			sprt.graphics.drawCircle( 0, this.height, this.selectedCircleRadius );
			sprt.graphics.drawCircle( this.width / 2, 0, this.selectedCircleRadius );
			sprt.graphics.drawCircle( this.width / 2, this.height, this.selectedCircleRadius );
			sprt.graphics.drawCircle( this.width, 0, this.selectedCircleRadius );
			sprt.graphics.drawCircle( this.width, this.height / 2, this.selectedCircleRadius );
			sprt.graphics.drawCircle( this.width, this.height, this.selectedCircleRadius );
		}

		public function updateDraw():void
		{
			this.drawclear();
			var doc:DisplayObjectContainer = this.parent;
			if ( doc )
			{
				doc.removeChild( this );
				doc.addChild( this );
			}
			drawpicture();
			if ( this.selectedState )
			{
				drawSelectedStyle();
			}
			if ( this.parentNode )
			{
				this.parentNode.getchildren().splice( this.parentNode.getchildren().indexOf( this ), 1 );
				this.parentNode.getchildren().unshift( this );
			}
		}

		public function getuic():UIComponent
		{
			return this;
		}

		public function changedirection( currentX:Number, currentY:Number ):int
		{
			if ( !this.selectedState )
			{
				return 0;
			}
			if ( getDistance( this.x, this.y, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 1;
			}
			if ( getDistance( this.x + this.width / 2, this.y, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 2;
			}
			if ( getDistance( this.x + this.width, this.y, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 3;
			}
			if ( getDistance( this.x + this.width, this.y + this.height / 2, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 4;
			}
			if ( getDistance( this.x + this.width, this.y + this.height, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 5;
			}
			if ( getDistance( this.x + this.width / 2, this.y + this.height, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 6;
			}
			if ( getDistance( this.x, this.y + this.height, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 7;
			}
			if ( getDistance( this.x, this.y + this.height / 2, currentX, currentY ) <= this.selectedCircleRadius )
			{
				return 8;
			}
			return 0;
		}


		public function drawpicture():void
		{

		}


		public function setposition( x:Number, y:Number ):void
		{
			this.x = x;
			this.y = y;
			this.rx = this.x + this.width / 2;
			this.ry = this.y + this.height / 2;
		}

		public function getwidth():Number
		{
			return this.width;
		}

		public function getheight():Number
		{
			return this.height;
		}

		public function isin( currentX:Number, currentY:Number ):int
		{
			return 0;
		}


		public function setsize( width:Number, height:Number ):void
		{
			this.width = width;
			this.height = height;
		}

		public function autosetsize( arrowX:Number, arrowY:Number, mode:Number ):void
		{
		}

		protected function getDistance( x1:Number, y1:Number, x2:Number, y2:Number ):Number
		{
			return Math.sqrt(( x2 - x1 ) * ( x2 - x1 ) + ( y2 - y1 ) * ( y2 - y1 ));
		}

		public function getContextPanel():Canvas
		{
			return null;
		}

		public function showContextPanel():void
		{

		}

		public function hideContextPanel():void
		{

		}

//		public function outputXMLhead():String{
//			var output:String="";
//			var i:int;
//			if(this.attributeName.length>0){
//				output+='<'+String(this.attributeValue[0]);
//			}
//			for(i=1;i<this.attributeName.length;i++){
//				output+=' '+String(this.attributeName[i])+'="'+String(this.attributeValue[i])+'"';
//			}
//			if(this.attributeName.length>0){
//				output+='>\n';
//			}
//			return output;
//		}
//		public function outputXMLtail():String{
//			var output:String="";
//			if(this.attributeName.length>0){
//				output+='</'+String(this.attributeValue[0])+'>\n';
//			}
//			return output;
//		}

//		public function getAttributeNameArray():Array{
//			return this.attributeName;
//		}
//		public function getAttributeValueArray():Array{
//			return this.attributeValue;
//		}
//		public function setAttributeValue(name:String,value:String):void{
//			if(this.attributeName.indexOf(name)!=-1){
//				this.attributeValue[this.attributeName.indexOf(name)]=value;
//			}
//			else{
//				if(name.length==0){
//					this.attributeName.unshift(name);
//					this.attributeValue.unshift(value);
//				}
//				else{
//					this.attributeName.push(name);
//					this.attributeValue.push(value);
//				}
//			}
//		}
//		public function getAttributeValue(name:String):String{
//			var ret:String;
//			if(this.attributeName.indexOf(name)!=-1){
//				ret=this.attributeValue[this.attributeName.indexOf(name)];
//			}
//			else{
//				ret=null;
//			}
//			return ret;
//		}
//		public function outputXML():String{
//			var ret:String="";
//			var i:int;
//			ret+=this.outputXMLhead();
//			for(i=0;i<this.children.length;i++){
//				ret+=IFigure(this.children[i]).outputXML();
//			}
//			ret+=this.outputXMLtail();
//			return ret;
//		}
//		public function getRelativePath(parentifigure:IFigure):String{
//			var str:String="";
//			var tempifi:IFigure;
//			var i:int;
//			if(parentifigure.haschild(this)){
//				for(i=0;i<parentifigure.getchildren().length;i++){
//					tempifi=IFigure(parentifigure.getchildren()[i]);
//					if(tempifi.haschild(this)||tempifi==this){
//						str+='/@';
//						if(tempifi.getAttributeNameArray().length>0){
//							str+=String(tempifi.getAttributeValueArray()[0]);
//							str+='.'+i.toString();
//						}
//						str+=this.getRelativePath(tempifi);
//						break;
//					}
//				}
//			}
//			return str;
//		}
//		public function clearAllAttributes():void{
//			var i:int;
//			var len:int;
//			len=this.attributeName.length;
//			for(i=0;i<len;i++){
//				this.attributeName.pop();
//			}
//			len=this.attributeValue.length;
//			for(i=0;i<len;i++){
//				this.attributeValue.pop();
//			}
//		}

		protected function GetDistancePointToLine( pointX:Number, pointY:Number, lineHeadX:Number, lineHeadY:Number, lineTailX:Number, lineTailY:Number ):Number
		{
			var ret:Number;
			ret = Math.abs((( lineTailY - lineHeadY ) * pointX + ( lineHeadX - lineTailX ) * pointY + lineTailY * ( lineTailX - lineHeadX ) + lineTailX * ( lineHeadY - lineTailY )) / Math.sqrt( Math.pow(( lineTailY - lineHeadY ), 2 ) + Math.pow( lineHeadX - lineTailX, 2 )));
			return ret;
		}

		//added by zjn
		public function getAttributeArray():ArrayCollection
		{

			return this.attribute.getAttributeArray();
		}

		//added by zjn
		public function setAttribute( attributes:ArrayCollection ):void
		{
			this.attribute.update( attributes );


		}

		public function outputAllInformation():XML
		{

			//Add by zair
			var orDesModelLoc:OrDesignerModelLocator = OrDesignerModelLocator.getInstance();
			var activeFEModel:FigureEditorModel = orDesModelLoc.getFigureEditorNavigatorModel().activeFigureEditorModel;
			var fileID:String;
			var figureEditorVH:FigureEditorVH;
			if ( activeFEModel )
				fileID = activeFEModel.fileID;
			if ( fileID )
				if ( FigureEditorVH.getViewHelperKey( fileID ))
					if ( ViewLocator.getInstance().registrationExistsFor( FigureEditorVH.getViewHelperKey( fileID )))
						figureEditorVH = ViewLocator.getInstance().getViewHelper( FigureEditorVH.getViewHelperKey( fileID )) as FigureEditorVH;

			//index figure type
			var figureType:String;
			if ( figureEditorVH )
			{
				if ( figureEditorVH.figureEditor )
				{
					if(activeFEModel.processType == FigureEditorModel.BPEL_PROCESS_TYPE)
					{
						figureType = FigureFactory.id2name( this.drawid );
					}
					else
						if(activeFEModel.processType == FigureEditorModel.BPMN_PROCESS_TYPE)
						{
							figureType = BpmnFigureFactory.id2name( this.drawid );
						}
				}
				else
					figureType = FigureFactory.id2name( this.drawid );
			}
			else
				figureType = FigureFactory.id2name( this.drawid );


			var info:XML = new XML( "<Figure></Figure>" );
			info.@type = figureType;
			info.@x = this.x;
			info.@y = this.y;
			info.@rx = this.rx;
			info.@ry = this.ry;
			info.@standardwidth = this.standardwidth;
			info.@standardheight = this.standardheight;
			info.@drawid = this.drawid;
			info.@width = this.width;
			info.@height = this.height;
			info.@multiple = this.multiple;
//			info.@premultiple=this.premultiple;
			info.@ID = this.ID;
			info.@figureName = this.figureName; // modified by zjn

			//added by zjn
			var attsXml:XML = new XML( "<Attributes></Attributes>" );
			var attArray:Array = this.attribute.getAttributeXML();
			for each ( var arr:XML in attArray )
			{
				attsXml.appendChild( arr );
			}

			info.appendChild( attsXml );

			return info;
		}

		public function readInformationToFigure( info:XML ):void
		{

			this.x = Number( info.@x );
			this.y = Number( info.@y );
			this.rx = Number( info.@rx );
			this.ry = Number( info.@ry );
			this.standardwidth = Number( info.@standardwidth );
			this.standardheight = Number( info.@standardheight );
			this.premultiple = Number( info.@multiple );
			this.multiple = Number( info.@multiple );


//			this.symbolwidth=Number(info.@symbolwidth);
//			this.symbolheight=Number(info.@symbolheight);

			this.drawid = Number( info.@drawid );

			this.setID( Number( info.@ID )); // modified by zjn
			var default_figureName:String = info.@figureName;
			if ( default_figureName != "" )
				this.figureName = info.@figureName;
			else
				info.@figureName = this.figureName; // modified by zjn

			this.width = Number( info.@width );
			this.height = Number( info.@height );

			//added by zjn
			var atts:XMLList = info.children();
			this.attribute.setAttribute( atts );
		}

		public function getMultiple():Number
		{
			return this.multiple;
		}

		public function setMultiple( mtp:Number ):void
		{
			this.premultiple = this.multiple;
			this.multiple = mtp;
		}

		protected function OutputScale( mtp:Number ):void
		{
			this.x = this.x / this.premultiple * this.multiple;
			this.y = this.y / this.premultiple * this.multiple;
			this.width = this.width / this.premultiple * this.multiple;
			this.height = this.height / this.premultiple * this.multiple;
			this.rx = this.rx / this.premultiple * this.multiple;
			this.ry = this.ry / this.premultiple * this.multiple;
			this.standardheight = this.standardheight / this.premultiple * this.multiple;
			this.standardwidth = this.standardwidth / this.premultiple * this.multiple;


			this.selectedCircleRadius = this.defaultSelectedCircleRadius * this.multiple;
			this.selectedLineThickness = this.defaultSelectedCircleRadius * this.multiple;
			this.lineThickness = this.defaultLineThickness * this.multiple;

			this.fontsize = defaultFontSize * this.multiple;

		}


	}
}
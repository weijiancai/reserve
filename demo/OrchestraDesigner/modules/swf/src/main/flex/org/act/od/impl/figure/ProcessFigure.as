package org.act.od.impl.figure
{

	import mx.binding.utils.BindingUtils;
	import mx.containers.Canvas;
	import mx.core.UIComponent;

	import org.act.od.impl.figure.bpmn.BpmnFigureFactory;
	import org.act.od.impl.model.FigureEditorModel;

    /**
	 *
	 * @author
	 *
	 */
	public class ProcessFigure implements IFigure
	{

		/**
		 * type of process (bpmn process or bpel process)
		 */
		[Bindable]
		public var processType:String;


		/**
		 *  contains all <code> IFigure </code> children
		 */
		public var children:Array;
		/**
		 * all attributes name.
		 */
		protected var attributeName:Array;
		/**
		 * all attributes value.
		 */
		protected var attributeValue:Array;

		[Bindable]
		public var multiple:Number=1;
		/**
		 *
		 */
		protected var premultiple:Number=1;

		/**
		 * Constructor,
		 * Initialized attributs.
		 */
		public function ProcessFigure()
		{
			children=new Array();
			attributeName=new Array();
			attributeValue=new Array();

			BindingUtils.bindSetter(this.OutputScale,this,"multiple");

		}

		/**
		 * Draw all picture of <code> children property </code>
		 */
		public function drawpicture():void
		{
			var i:int;
			for(i=0;i<children.length;i++){
				IFigure(children[i]).drawpicture();
			}
		}

		/**
		 * TODO
		 * @return
		 *
		 */
		public function getuic():UIComponent
		{
			return null;
		}


		/**
		 * TODO
		 *
		 */
		public function drawclear():void
		{
		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function getstandardwidth():Number
		{
			return 0;
		}

//		public function getsymbolheight():Number
//		{
//			return 0;
//		}
//
//		public function getsymbolwidth():Number
//		{
//			return 0;
//		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function getstandardheight():Number
		{
			return 0;
		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function getdrawid():Number
		{
			return 0;
		}

		/**
		 * TODO
		 * @return
		 *
		 */
		public function setxy(x:Number,y:Number):void{

		}

		/**
		 * TODO
		 * @return
		 *
		 */
		public function getx():Number
		{
			return 0;
		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function gety():Number
		{
			return 0;
		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function getrx():Number
		{
			return 0;
		}

		/**
		 * TODO
		 * @return
		 *
		 */
		public function getry():Number
		{
			return 0;
		}
				/**
		 * TODO
		 * @return
		 *
		 */

		public function isin(currentX:Number, currentY:Number):int
		{
			return 0;
		}
		/**
		 * return <code> children </code> attribut.
		 * @return children
		 *
		 */
		public function getchildren():Array
		{
			return children;
		}

		/**
		 * Check if the <code> children </code> property contains the child parametor.
		 * <p> retur true in this case, else return false.  </p>
		 *
		 * @param child IFigure to be checked.
		 *
		 * @return Boolean result of the check out
		 *
		 */
		public function haschild(child:IFigure):Boolean{
			var i:int;
			if(child.getparent()==this){
				return true;
			}
			for(i=0;i<children.length;i++){
				if(IFigure(children[i]).haschild(child)){
					return true;
				}
			}
			return false;
		}

		/**
		 * search the child with id given in parametor.
		 * <p> return child figure when it's found, else return null</p>
		 *
		 * @param id  child id.
		 *
		 * @return child figure found.
		 *
		 */
		public function searchchildWithId(id:String):IFigure{

//			if(this.getAttributeValue("id")==id){
//				return this;
//			}

			var i:int;
			for(i=0;i<this.children.length;i++){

				//not consider container figure
//				ifi=IFigure(this.children[i]).searchchildWithId(Id);
//				if(ifi){
//					break;
//				}
				var childFigure:AbstractFigure = AbstractFigure(this.children[i]);
				if(childFigure.ID.toString() == id)
					return childFigure;
			}

			return null;
		}

		/**
		 * add an figure child to children
		 * @param child IFigure child to be added.
		 *
		 */
		public function addchild(child:IFigure):void
		{
			if(haschild(child)){
				removechildWithoutConnection(child);
//				child.getchildren().splice(child.getchildren().indexOf(child),0);
//				return;
			}

			var i:int;
			/*var cf:ConnectionFigure;
			child.updateDraw();
			for(i=0;i<children.length;i++){
				IFigure(children[i]).addchild(child);
				if(IFigure(children[i]).haschild(child)){
					break;
				}
			}

			if(haschild(child)==false){
				child.setparent(this);
				if(child is ConnectionFigure){
					cf=ConnectionFigure(child);
					if(GraphicalFigure(cf.getEndFigure()).getEndConnection().indexOf(cf)==-1){
						GraphicalFigure(cf.getEndFigure()).getEndConnection().push(cf);
					}
					if(GraphicalFigure(cf.getStartFigure()).getStartConnection().indexOf(cf)==-1){
						GraphicalFigure(cf.getStartFigure()).getStartConnection().push(cf);
					}
				}
				children.push(child);
			}*/
		}

		/**
		 * Add child with connection
		 * @param child child IFigure child to be added.
		 *
		 */
		public function addchildWithConnection(child:IFigure):void{
			var i:int;
			/*var gf:GraphicalFigure;
			var arr:Array;
			var len:int;

			addchild(child);

			if(child is GraphicalFigure){
				gf=GraphicalFigure(child);
				arr=gf.getStartConnection();
				len=arr.length;
				for(i=0;i<len;i++){
					if(child.getrootfigure().haschild(ConnectionFigure(arr[i]).getEndFigure())){
						child.getrootfigure().addchild(IFigure(arr[i]));
					}
				}
				arr=gf.getEndConnection();
				len=arr.length;
				for(i=0;i<len;i++){
					if(child.getrootfigure().haschild(ConnectionFigure(arr[i]).getStartFigure())){
						child.getrootfigure().addchild(IFigure(arr[i]));
					}
				}
			}*/
		}
		/**
		 * Remove child with connection.
		 *
		 * @param child child IFigure child to be removed.
		 * @param headarr
		 * @param tailarr
		 *
		 */
		public function removechildWithConnection(child:IFigure,headarr:Array,tailarr:Array):void{
			var i:int;
			var j:int;
			var l:int;
			var line:Array;
			var otherline:Array;
			/*if(haschild(child)){
				child.drawclear();
				if(child is GraphicalFigure){
					line=GraphicalFigure(child).getStartConnection();
					j=line.length;
					for(i=0;i<j;i++){
						headarr[i]=line[0];
						this.removechildWithoutConnection(line[0]);
//						otherline=GraphicalFigure(ConnectionFigure(line[0]).getEndFigure()).getEndConnection();
//						otherline.splice(otherline.indexOf(ConnectionFigure(line[0])),1);
//						headarr[i]=line.shift();
					}
					line=GraphicalFigure(child).getEndConnection();
					j=line.length;
					for(i=0;i<j;i++){
						tailarr[i]=line[0];
						this.removechildWithoutConnection(line[0]);
//						otherline=GraphicalFigure(ConnectionFigure(line[i]).getStartFigure()).getStartConnection();
//						otherline.splice(otherline.indexOf(ConnectionFigure(line[i])),1);
//						tailarr[i]=line.shift();
					}
				}
			}
			else{
				return;
			}*/
			removechildWithoutConnection(child);
		}

		/**
		 * Remove child without connection
		 *
		 * @param child child IFigure child to be removed.
		 *
		 */
		public function removechildWithoutConnection(child:IFigure):void
		{
			var i:int;
			var j:int;
			var l:int;
			if(haschild(child)){
				child.drawclear();
			}
			else{
				return;
			}
			var line:Array;
			for(i=0;i<children.length;i++){
				if(IFigure(children[i])==child){
					child.setparent(null);
					children.splice(i,1);
					if(child.getchildren()){
						l=child.getchildren().length;
						for(j=0;j<l;j++){
							addchild(IFigure(child.getchildren().shift()));
						}
					}
					i--;
					/*if(child is ConnectionFigure){
						line=GraphicalFigure(ConnectionFigure(child).getStartFigure()).getStartConnection();
						line.splice(line.indexOf(child),1);
						line=GraphicalFigure(ConnectionFigure(child).getEndFigure()).getEndConnection();
						line.splice(line.indexOf(child),1);
					}*/
				}
				else{
					IFigure(children[i]).removechildWithoutConnection(child);
				}
			}
		}
		/**
		 * TODO
		 * @param x
		 * @param y
		 *
		 */
		public function setposition(x:Number,y:Number):void{

		}
		/**
		 * TODO
		 * @return
		 *
		 */
		public function getparent():IFigure{
			return null;
		}
		/**
		 * TODO
		 * @param parent
		 *
		 */
		public function setparent(parent:IFigure):void{

		}

		/**
		 * Return the root figure of this figure
		 *
		 * @return IFigure root figure.
		 *
		 */
		public function getrootfigure():IFigure{
			var ifi:IFigure=this;
			while(ifi.getparent()){
				ifi=ifi.getparent();
			}
			return ifi;
		}
		/**
		 * Return the upper figure corresponding to these coordinates (x,y).
		 * @param x coordinate number.
		 * @param y cordinate number.
		 * @param flag
		 * @return  IFigure
		 *
		 */
		public function getupperfigure(x:Number,y:Number,flag:int=-1):IFigure{
			var temp:IFigure=null;
			var ret:IFigure=null;
			var i:int;
			if(this.getchildren()){
				for(i=0;i<this.getchildren().length;i++){
					temp=IFigure(this.getchildren()[i]).getupperfigure(x,y,flag);
					if(temp!=null){
						if(flag==-1){
							if(ret){
								if(ret.isin(x,y)<temp.isin(x,y)){
									ret=temp;
								}
							}
							else{
								ret=temp;
							}
						}
						else{
							ret=temp;
							break;
						}
					}
				}
			}
			return ret;
		}
			/**
		 * TODO
		 * @param width
		 * @param height
		 *
		 */
		public function setsize(width:Number,height:Number):void{

		}
		/**
		 * TODO
		 * @param arrowX
		 * @param arrowY
		 * @param mode
		 *
		 */
		public function autosetsize(arrowX:Number,arrowY:Number,mode:Number):void{

		}
		/**
		 * add children to an array structure
		 * @param ar Array parametor.
		 *
		 */
		public function ifiguretoarray(ar:Array):void{
			var i:int;
			for(i=0;i<children.length;i++){
				IFigure(children[i]).ifiguretoarray(ar);
			}
		}
		/**
		 * TODO
		 * @return 0
		 *
		 */
		public function getdrawx():Number{
			return getx();
		}
		/**
		 * TODO
		 * @return  0
		 *
		 */
		public function getdrawy():Number{
			return gety();
		}
		/**
		 * TODO
		 * @return True
		 *
		 */
		public function isSelected():Boolean{
			return true;
		}
		/**
		 * TODO
		 * @param isslct
		 *
		 */
		public function setSelected(isslct:Boolean):void{

		}
		/**
		 * TODO
		 *
		 */
		public function drawSelectedStyle():void{

		}
		/**
		 * Update draw of children.
		 * <p> repaint all. </p>
		 *
		 */
		public function updateDraw():void{
			var i:int;
			for(i=0;i<children.length;i++){
				IFigure(children[i]).updateDraw();
			}
		}
		/**
		 * TODO
		 * @param currentX
		 * @param currentY
		 * @return 0
		 *
		 */
		public function changedirection(currentX:Number,currentY:Number):int{
			return 0;
		}
		/**
		 * TODO
		 * @return 0
		 *
		 */
		public function getheight():Number{
			return 0;
		}
		/**
		 * TODO
		 * @return 0
		 *
		 */
		public function getwidth():Number{
			return 0;
		}
		/**
		 * TODO
		 * @param ID
		 *
		 */
		public function setID(ID:int):void{

		}
		/**
		 * TODO
		 * @return  -1
		 *
		 */
		public function getID():int{
			return -1;
		}
		/**
		 * TODO
		 * @return null
		 *
		 */
		public function getContextPanel():Canvas{
			return null;
		}
		/**
		 * TODO
		 *
		 */
		public function showContextPanel():void{

		}
		/**
		 * TODO
		 *
		 */
		public function hideContextPanel():void{

		}
		/**
		 *reset <code> children </code> property.
		 *
		 */
		public function resetChildren():void {
			this.children = new Array();
		}

		/**
		 * TODO
		 * @return  false
		 *
		 */
		public function getIsShowContextPanel():Boolean{
			return false;
		}
		/**
		 * TODO
		 * @param isShowContextPanel
		 *
		 */
		public function setIsShowContextPanel(isShowContextPanel:Boolean):void{

		}


		/**
		 * Out put all information of children property.
		 *
		 * @return XML children information.
		 *
		 */
		public function outputAllInformation():XML{

			var processXML :XML=new XML("<Process></Process>");

			if(this.processType)
				processXML.@processType = this.processType;

			var i:int;
			for(i=0;i<this.children.length;i++){
				processXML.appendChild(IFigure(this.children[i]).outputAllInformation());
			}

			return processXML;
		}

		/**
		 * Read information to XML figure.
		 *
		 * @param info XML figure.
		 *
		 */
		public function readInformationToFigure(info:XML):void{

			var list:XMLList=info.elements("Process");

			//clear children for reset
			this.resetChildren();

			var fig:IFigure;
			var connection :Array=new Array();
			list=XML(info).elements("Figure");
			var i:int;

			this.processType = String(info.@processType);

			/*for(i=0;i<list.length();i++)
			{
				if(this.processType == FigureEditorModel.BPEL_PROCESS_TYPE )
					fig = FigureFactory.createFigure(int(XML(list[i]).@drawid));
				else
					if(this.processType == FigureEditorModel.BPMN_PROCESS_TYPE )
						fig = BpmnFigureFactory.createFigure(int(XML(list[i]).@drawid));

				fig.readInformationToFigure(XML(list[i]));

				if(fig is ConnectionFigure){
					connection.push(fig);
				}
				else{
					this.addchild(fig);
				}
			}

			var cf:ConnectionFigure;

			for(i=0;i<connection.length;i++){
				cf=ConnectionFigure(connection[i]);
				cf.setStartFigure(this.searchchildWithId(cf.getStartFigureId().toString()));
				cf.setEndFigure(this.searchchildWithId(cf.getEndFigureId().toString()));
				this.addchild(cf);
			}*/
 		}
 		/**
		 * Set <code> multiple </code> property by given parametor.
		 *
		 * @param mtp
		 *
		 */
 		public function setMultiple(mtp:Number):void{
			this.premultiple=this.multiple;
			this.multiple=mtp;
 		}
 		/**
		 * Get <code> multiple </code> property.
		 *
		 * @return multiple
		 *
		 */
 		public function getMultiple():Number{
			return this.multiple;
 		}

 		/**
		 * TODO
		 * @param mtp
		 *
		 */
		protected function OutputScale(mtp:Number):void{

		}

	}
}
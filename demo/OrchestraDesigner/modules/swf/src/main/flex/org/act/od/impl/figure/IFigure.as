package org.act.od.impl.figure
{
	
	import mx.containers.Canvas;
	import mx.core.UIComponent;

/*	
	public var INIT:int=0;
	public var UP:int=2;
	public var DOWN:int=6;
	public var LEFT:int=8;
	public var RIGHT:int=4;
	public var LEFTUP:int=1;
	public var RIGHTUP:int=3;
	public var LEFTDOWN:int=7;
	public var RIGHTDOWN:int=5;
*/

	

	public interface IFigure
	{
		/**
		 *draw picture 
		 * 
		 */
		function drawpicture():void;
		/**
		 *repaint all 
		 * 
		 */		
		function updateDraw():void;
		/**
		 *set coordinate 
		 * @param x
		 * @param y
		 * 
		 */		
		function setposition(x:Number,y:Number):void;
		/**
		 *get UI Component 
		 * @return 
		 * 
		 */		
		function getuic():UIComponent;
		/**
		 *clear draw 
		 * 
		 */		
		function drawclear():void;
		/**
		 *get minimum width 
		 * @return 
		 * 
		 */		
		function getstandardwidth():Number;
		/**
		 *get minimum height 
		 * @return 
		 * 
		 */		
		function getstandardheight():Number;
		/**
		 *get class id 
		 * @return 
		 * 
		 */		
		function getdrawid():Number;
		/**
		 *set left angle's coordinates 
		 * @param x
		 * @param y
		 * 
		 */		
		function setxy(x:Number,y:Number):void
		/**
		 *set left angle's x coordinate 
		 * @return 
		 * 
		 */		
		function getx():Number;
		/**
		 *set left angle's y coordinate 
		 * @return 
		 * 
		 */		
		function gety():Number;
		/**
		 *set center's x coordinate 
		 * @return 
		 * 
		 */		
		function getrx():Number;
		/**
		 *set center's y coordinate 
		 * @return 
		 * 
		 */		
		function getry():Number;
		/**
		 *get draw's x coordinate 
		 * @return 
		 * 
		 */		
		function getdrawx():Number;
		/**
		 *get draw's y coordinate 
		 * @return 
		 * 
		 */		
		function getdrawy():Number;
		/**
		 *get figure's width 
		 * @return 
		 * 
		 */		
		function getwidth():Number;
		/**
		 *get figure's height 
		 * @return 
		 * 
		 */		
		function getheight():Number;
		/**
		 *judge whether the mouse is clicking in the figure 
		 * @param currentX
		 * @param currentY
		 * @return 
		 * 
		 */		
		function isin(currentX:Number,currentY:Number):int;
		/**
		 *judge which direction is selected
		 * 1~8 represent eight directions
		 * if no direction is selected return 0 
		 * @param currentX
		 * @param currentY
		 * @return 
		 * 
		 */		
		function changedirection(currentX:Number,currentY:Number):int;
		/**
		 *judge whether the figure is selected 
		 * @return 
		 * 
		 */		
		function isSelected():Boolean;
		/**
		 *set selected state 
		 * @param isslct
		 * 
		 */		
		function setSelected(isslct:Boolean):void;
		/**
		 *set figure size 
		 * @param width
		 * @param height
		 * 
		 */		
		function setsize(width:Number,height:Number):void;
		/**
		 *automatically adjust the figure's size according to current mouse arrow position
		 * @param arrowX
		 * @param arrowY
		 * @param mode : selected directions
		 * 
		 */		
		function autosetsize(arrowX:Number,arrowY:Number,mode:Number):void;

		/**
		 *set parent figure 
		 * @param parent
		 * 
		 */
		function setparent(parent:IFigure):void;
		/**
		 *get parent figure 
		 * @return 
		 * 
		 */		
		function getparent():IFigure;
		/**
		 *get root figure 
		 * @return 
		 * 
		 */		
		function getrootfigure():IFigure;
		/**
		 *get child figures' list 
		 * @return 
		 * 
		 */		
		function getchildren():Array;
		/**
		 *add child node 
		 * @param child
		 * 
		 */		
		function addchild(child:IFigure):void;
		/**
		 *add child node and connection figures which is connected to this node 
		 * @param child
		 * 
		 */		
		function addchildWithConnection(child:IFigure):void;

		/**
		 *remove child node without its connections 
		 * @param child
		 * 
		 */
		function removechildWithoutConnection(child:IFigure):void;
		/**
		 *remove child node with its connections
		 * @param child
		 * @param headarr
		 * @param tailarr
		 * 
		 */		
		function removechildWithConnection(child:IFigure,headarr:Array,tailarr:Array):void;
		/**
		 *determine whether contains this child 
		 * @param child
		 * @return 
		 * 
		 */		
		function haschild(child:IFigure):Boolean;
		/**
		 *get upper figure according to current mouse position 
		 * @param x
		 * @param y
		 * @param flag
		 * @return 
		 * 
		 */		
		function getupperfigure(x:Number,y:Number,flag:int=-1):IFigure;
		/**
		 *add children to an array structure
		 * @param ar
		 * 
		 */		
		function ifiguretoarray(ar:Array):void;
		/**
		 *draw the selecting line 
		 * 
		 */		
		function drawSelectedStyle():void;
		/**
		 *judge weather to show context panel or not 
		 * @return 
		 * 
		 */		
		function getIsShowContextPanel():Boolean;
		/**
		 *set context panel showing state 
		 * @param isshowcontextpanel
		 * 
		 */		
		function setIsShowContextPanel(isshowcontextpanel:Boolean):void;
		/**
		 *get context panel 
		 * @return 
		 * 
		 */		
		function getContextPanel():Canvas;
		/**
		 *show context panel 
		 * 
		 */		
		function showContextPanel():void;
		/**
		 *hide context panel 
		 * 
		 */		
		function hideContextPanel():void;
		/**
		 *get figure id 
		 * @return 
		 * 
		 */		
		function getID():int;
		/**
		 *set figure id 
		 * @param ID
		 * 
		 */		
		function setID(ID:int):void;
		/**
		 *figures serialize 
		 * @return 
		 * 
		 */		
		function outputAllInformation() :XML;
		/**
		 *figures deserialize 
		 * @param info
		 * @param container
		 * 
		 */		
		function readInformationToFigure(info:XML):void;
		/**
		 *set display multiple 
		 * @param multiple
		 * 
		 */		
		/**
		 *set display multiple 
		 * @param multiple
		 * 
		 */		
		function setMultiple(multiple:Number):void;
		/**
		 *get display multiple 
		 * @return 
		 * 
		 */		
		function getMultiple():Number;
	}
}

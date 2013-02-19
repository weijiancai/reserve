package org.act.od.impl.events
{
	import org.act.od.framework.control.OrDesignerEvent;
	/**
	 * This class define the events raised by the FigureCanvas.
	 */
	public class FigureCanvasAppEvent extends OrDesignerEvent{
		/**
		 * Pop up the FigureRemoveConfirm window.
		 */
		public static const POP_FIGURE_DELETE_CONFIRM :String = "pop_figure_delete_confirm_FigureCanvas";
		/**
		 * Delecte a figure from the canvas.
		 */
		public static const FIGURE_DELETE_FROM_CANVAS :String = "figure_delete_from_canvas_FigureCanvas";
		/**
		 * Copy the selected figure from the canvas.
		 */
		public static const FIGURES_COPY :String = "figures_copy_FigureCanvas";
		/**
		 * Paste the selected figure to the canvas.
		 */
		public static const FIGURES_PASTE :String = "figures_paste_FigureCanvas";
		/**
		 * Change the figure's size in the canvas.
		 */
		public static const CHANGE_FIGURE_SIZE_IN_CANVAS : String = "change_figure_size_in_canvas_FigureCanvas";
		/**
		 * Create the start point of the connection figure.
		 */
		public static const CREATE_CONNECTION_START : String = "create_connection_start_FigureCanvas";
		/**
		 * Create the end point of the connection figure.
		 */
		public static const CREATE_CONNECTION_END : String = "create_connection_end_FigureCanvas";
		/**
		 * Zoom in the canvas.
		 */
		public static const Zoom_In :String = "zoom_in";
		/**
		 * Zoom out the canvas.
		 */
		public static const Zoom_Out :String = "zoom_out";
		/**
		 * Pop up the "About Orchestra Designer" Window.
		 */
		public static const About_Info :String = "about_info";
		/**
		 * Select all figures in the canvas.
		 */
		public static const Select_All :String = "select_all";
		
		public static const Node_Create_Link :String = "node_create_link";
		/**
		 * Open the subprocess file.
		 */
		public static const OPEN_SUBPROCESS : String = "open_subprocess_FigureCanvas";
		/**
		 * Create a new subprocess figure.
		 */
		public static const CREATE_NEW_SUBPROCESS : String = "create_new_subprocess_SubProcess";
		/**
		 * Move the figure left.
		 */
		public static const MOVE_LEFT : String = "move_left";
		/**
		 * Move the figure up.
		 */
		public static const MOVE_UP : String = "move_up";
		/**
		 * Move the figure right.
		 */
		public static const MOVE_RIGHT : String = "move_right";
		/**
		 * Move the figure down.
		 */
		public static const MOVE_DOWN : String = "move_down";
		/**
		 * Change the Microimage panel.
		 */
		public static const Canvas_Children_Changed : String = "canvas_children_changed";
		/**
		 * Constructor, takes the event name (type) and data object (defaults to null)
		 * and also defaults the standard Flex event properties bubbles and cancelable
		 * to true and false respectively.
		 */ 
		public function FigureCanvasAppEvent(type : String, dataParameter:Object = null ,
				bubbles : Boolean = false, cancelable : Boolean = false){
			
			super(type, dataParameter, bubbles, cancelable);
		}

	}
}
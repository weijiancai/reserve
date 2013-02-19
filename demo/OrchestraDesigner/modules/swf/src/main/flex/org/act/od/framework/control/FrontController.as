package org.act.od.framework.control{   
	
   
   import flash.utils.Dictionary;
   
   import org.act.od.framework.commands.IODCommand;
   import org.act.od.framework.other.OrDesignerError;
   import org.act.od.framework.other.OrDesignerMessageCodes;
   
   /**
    * A base class for an application specific front controller,
    * that is able to dispatch control following particular user gestures to appropriate
    * command classes.
    *
    * <p>
    * The Front Controller is the centralised request handling class in a
    * Cairngorm application.  Throughout the application architecture are
    * scattered a number of CairngormEventDispatcher.getInstance().dispatchEvent( event )
    * method calls, that signal to the listening controller that a user gesture
    * has occured.
    * </p>
    *
    * <p>
    * The role of the Front Controller is to first register all the different
    * events that it is capable of handling against worker classes, called
    * command classes.  On hearing an application event, the Front Controller
    * will look up its table of registered events, find the appropriate
    * command for handling of the event, before dispatching control to the
    * command by calling its execute() method.
    * </p>
    *
    * <p>
    * Commands are added to the front controller with a weak reference,
    * meaning that when the command is garbage collected, the reference in
    * the controller is also garbage collected.
    * </p>
    * 
    * <p>
    * The Front Controller is a base-class that  listen for events 
    * dispatched by CairngormEventDispatcher.  In a 
    * Cairngorm application, the developer should create a class that
    * extends the FrontController, and in the constructor of their
    * application specific controller, they should make numerous calls to
    * addCommand() to register all the expected events with application
    * specific command classes.
    * </p>
    *
    * <p>
    * Consider a LoginController, that is the main controller for a Login
    * application that has 2 user gestures - Login and Logout.  The application
    * will have 2 buttons, "Login" and "Logout" and in the click handler for
    * each button, one of the following methods is executed:
    * </p>
    *
    * <pre>
    * public function doLogin() : void
    * {
    *    var event : LoginEvent = new LoginEvent( username.text, password.text );
    *    CairngormEventDispatcher.getInstance.dispatchEvent( event );
    * }
    * 
    * public function doLogout() : void
    * {
    *    var event : LogoutEvent = new LogoutEvent();
    *    CairngormEventDispatcher.getInstance.dispatchEvent( event );
    * }
    * </pre>
    * 
    * <p>
    * We would create LoginController as follows:
    * </p>
    *
    * <pre>
    * class LoginController extends com.adobe.cairngorm.control.FrontController
    * {
    *    public function LoginController()
    *    {
    *       initialiseCommands();
    *    }
    * 
    *    public function initialiseCommands() : void
    *    {
    *       addCommand( LoginEvent.EVENT_LOGIN, LoginCommand );
    *       addCommand( LogoutEvent.EVENT_LOGOUT, LogoutCommand );
    *    }
    *   
    * }
    * </pre>
    *
    * <p>
    * In our concrete implementation of a FrontController, LoginController, we
    * register the 2 events that are expected for broadcast - login and logout -
    * using the addCommand() method of the parent FrontController class, to
    * assign a command class to each event.
    * </p>
    *
    * <p>
    * Adding a new use-case to a Cairngorm application is as simple as
    * registering the event against a command in the application Front Controller,
    * and then creating the concrete command class.
    * </p>
    * 
    * <p>
    * The concrete implementation of the FrontController, LoginController,
    * should be created once and once only (as we only want a single controller
    * in our application architecture).  Typically, in our main application, we
    * would declare our FrontController child class as a tag, which should be placed
    * above any tags which have a dependency on the FrontController
    * </p>
    *
    * <pre>
    * &lt;mx:Application  xmlns:control="com.domain.project.control.LoginController"   ... &gt;
    *
    *   &lt;control:LoginController id="controller" /&gt;
    *
    *  ...
    * 
    * </pre>
    *
    * @see com.adobe.cairngorm.commands.ICommand
    */
    
   public class FrontController{
   	
     /**
      * Dictionary of event name to command class mappings
      */ 
      protected var commands : Dictionary = new Dictionary();

     /**
      * Registers a ICommand class with the Front Controller, against an event name
      * and listens for events with that name.
      *
      * <p>When an event is broadcast that matches commandName,
      * the ICommand class referred to by commandRef receives control of the
      * application, by having its execute() method invoked.</p>
      *
      * @param commandName The name of the event that will be broadcast by the
      * when a particular user gesture occurs, eg "login"
      *
      * @param commandRef An ICommand Class reference upon which execute()
      * can be called when the Front Controller hears an event broadcast with
      * commandName. Typically, this argument is passed as "LoginCommand" 
      * or similar.
      * 
      * @param useWeakReference A Boolean indicating whether the controller
      * should added as a weak reference to the CairngormEventDispatcher,
      * meaning it will eligibile for garbage collection if it is unloaded from 
      * the main application.
      */     
      public function addCommand( commandName : String, commandRef : Class, useWeakReference : Boolean = true ) : void{
      	
         if( commands[ commandName ] != null )
            throw new OrDesignerError( OrDesignerMessageCodes.COMMAND_ALREADY_REGISTERED, commandName );
         
         commands[ commandName ] = commandRef;
         OrDesignerEventDispatcher.getInstance().addEventListener( commandName, executeCommand, false, 0, useWeakReference );
      }
      
     /**
      * Deregisters an ICommand class with the given event name from the Front Controller 
      *
      * @param commandName The name of the event that will be broadcast by the
      * when a particular user gesture occurs, eg "login"
      *
      */     
      public function removeCommand( commandName : String ) : void{
      	
         if( commands[ commandName ] === null)
            throw new OrDesignerError( OrDesignerMessageCodes.COMMAND_NOT_REGISTERED, commandName);  
         
         OrDesignerEventDispatcher.getInstance().removeEventListener( commandName, executeCommand );
         commands[ commandName ] = null;
         delete commands[ commandName ]; 
      }
      
     /**
      * Executes the command
      */  
      protected function executeCommand( event : OrDesignerEvent ) : void{
      	
         var commandToInitialise : Class = getCommand( event.type );
         var commandToExecute : IODCommand = new commandToInitialise();

         commandToExecute.execute( event );
      }
      
      /**
      * unexecute the command
      */
//      protected function unExecuteCommand():void{
//      		var command :IODCommand = commandStack.pop();
//      		if(command != null)
//      			command.unExecute();
//      }
     /**
      * Returns the command class registered with the command name. 
      */
      protected function getCommand( commandName : String ) : Class{
      	
         var command : Class = commands[ commandName ];
         
         if ( command == null )
            throw new OrDesignerError( OrDesignerMessageCodes.COMMAND_NOT_FOUND, commandName );
            
         return command;
      }      
   }   
}

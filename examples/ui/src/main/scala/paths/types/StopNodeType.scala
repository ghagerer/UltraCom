package ui.paths.types

import org.mt4j.Application
import org.mt4j.components.visibleComponents.shapes.MTEllipse
import org.mt4j.components.visibleComponents.shapes.MTPolygon
import org.mt4j.components.visibleComponents.StyleInfo

import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor 
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor 
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent
import org.mt4j.input.inputProcessors.IGestureEventListener
import org.mt4j.input.inputProcessors.MTGestureEvent
import org.mt4j.input.gestureAction.DefaultDragAction
import org.mt4j.input.gestureAction.InertiaDragAction

import org.mt4j.util.math.Vector3D
import org.mt4j.util.math.Vertex
import processing.core.PGraphics
import processing.core.PConstants._

import org.mt4j.util.Color

import ui._
import ui.util._
import ui.events._
import ui.paths._
import ui.usability._

/**
* This is the stop node type.
* A node associated with this type functions as a stop signal for the playing back of a path,
* that is, once the ende node is reached, playback stops.
*/
object StopNodeType extends EndNodeType{

  val Symbol = Rectangle
  
  protected override def setupInteractionImpl(app: Application, node: Node) = {
    super.setupInteractionImpl(app, node)     
    
    node.registerInputProcessor(new TapProcessor(app))       
    node.addGestureListener(classOf[TapProcessor], new IGestureEventListener() {
        override def processGestureEvent(gestureEvent: MTGestureEvent): Boolean = {
              gestureEvent match {
                  case tapEvent: TapEvent => {
                      if (tapEvent.getTapID == TapEvent.BUTTON_DOWN) {
                        println("stop down")
                        node.setTapped(true)
                      }
                      else if (tapEvent.getTapID == TapEvent.BUTTON_UP) {
                        println("stop up")
                        node.setTapped(false)
                        node.associatedPath.foreach(_ ! UiEvent("IGNORE_IGNORE_NEXT_STOP_PLAYBACK"))
                      }
                      else if (tapEvent.getTapID == TapEvent.BUTTON_CLICKED) {
                        println("stop clicked")
                        node.setTapped(false)
                        node.associatedPath.foreach(_ ! UiEvent("STOP_PLAYBACK"))
                      }
                      true
                  }
                  case someEvent => {
                      println("I can't process this particular event: " + someEvent.toString)
                      false
                  }
              }
        }
    })
  }       
  
  
  override def symbol = {
    Some(this.Symbol)
  }
  
  
  override def toString = {
    "StopNode"
  }  
      
}

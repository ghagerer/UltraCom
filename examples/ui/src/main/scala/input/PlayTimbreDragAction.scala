package ui.input

import org.mt4j.Application

import org.mt4j.input.gestureAction.DefaultDragAction
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent

import org.mt4j.input.inputProcessors.IGestureEventListener
import org.mt4j.input.inputProcessors.MTGestureEvent

import scala.actors._

import ui.paths._
import ui.events._
import ui.audio._
import ui.properties.types._
import ui.menus.main._
import ui._

class PlayTimbreDragAction(node: Node) extends BoundedDragAction(Menu.Space, Menu.Space, Ui.width - Menu.Space, Ui.height - Menu.Space) {
  
  	override def processGestureEvent(gestureEvent: MTGestureEvent): Boolean = {  
  	  val returnValue = super.processGestureEvent(gestureEvent)
  	  //println("play timbre drag action")
      gestureEvent match {
        case dragEvent: DragEvent => {
          dragEvent.getId match {
            case MTGestureEvent.GESTURE_DETECTED => {println("detected"); this.sendPlayAudioEvent()}
            case MTGestureEvent.GESTURE_RESUMED => {println("resumed"); this.sendPlayAudioEvent()}
            case MTGestureEvent.GESTURE_UPDATED => {println("updated"); this.sendPlayAudioEvent()}
            case MTGestureEvent.GESTURE_CANCELED => {println("canceled"); this.sendStopAudioEvent()}
            case MTGestureEvent.GESTURE_ENDED => {println("ended"); this.sendStopAudioEvent()}
            case somethingElse => {println("some other gesture event type")}
          }
        }
      }  	  
  	  returnValue
  	}
  	
  	private def sendPlayAudioEvent() = {
      val channels = node match {
        case withChannels: AudioChannels => withChannels.collectOpenChannels
        case withoutChannels => Array(0,1,2,3)
      } 
      
      val (uiX, uiY) =  (node.position._1.toInt, node.position._2.toInt)
      val (x, y) = (uiX/Ui.width.toFloat, uiY/Ui.height.toFloat)
      
      node match {
        case singleNode: SingleNode => Ui.audioInterface ! PlayAudioEvent(node.id, x, y, singleNode.getPropertyValue(PitchPropertyType), singleNode.getPropertyValue(VolumePropertyType), channels)
        case otherNode => Ui.audioInterface ! PlayAudioEvent(node.id, x, y, PitchPropertyType.mean, VolumePropertyType.mean, channels)
      }
    }
    
    
    private def sendStopAudioEvent() = {
      Ui.audioInterface ! StopAudioEvent(node.id)          
    }
  	
}

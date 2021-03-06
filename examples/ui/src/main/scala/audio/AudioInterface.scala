package ui.audio

import org.mt4j.util.Color

import scala.actors._

//import ugen._
import org.mt4j.output.audio.AudioServer
import org.mt4j.output.audio.AudioServer._
import de.sciss.synth._
import de.sciss.synth.ugen._
import de.sciss.synth.Ops._


object AudioInterface {
  
  def apply(timbreSpace: TimbreSpace) = {
    new AudioInterface(timbreSpace)
  }
  
}

/**
* This class represents an audio interface.
* An audio interface is associated with a timbre space and may receive audio events.
* These events are then processed and may trigger actions on the actual audio server.
* 
*/
class AudioInterface(val timbreSpace: TimbreSpace) extends Actor {
  
  /**
  * The number of output channels.
  */
  val Channels = 4
  
  /**
  * Maps caller ids to synths.
  */
  var synthMap = Map[Int, Synth]()
  
  this.start
  
  def play(event: PlayAudioEvent) = {
    this.synchronized {
      //println("in play")
      if (this.synthMap.contains(event.callerID)) {
        //println("updating synth with caller id " + event.callerID)
        this.updateParameters(this.synthMap(event.callerID), event)
      }
      else {
        val synthDef = this.timbreSpace.synthDefinition(event.x, event.y, event.pitch, event.volume)
        println("starting synth with caller id " + event)
        this.synthMap += (event.callerID -> synthDef.play())
        Thread.sleep(50)
        /* 
        the sleep call is actually a workaround for cases where play and stop are called in rapid succession;
        apparently, if a synth is freed too quickly after it is instantiated, Super Collider does not have a Node
        for it yet and thus the free call fails; since the synth is nonetheless removed from the synth map of this class, 
        there is then no way to stop the synth again, which is highly undesirable behaviour
        */
      }    
      //println(this.synthMap.toString)
   }
  }
  
  def stop(event: StopAudioEvent) = {
    this.synchronized {
      //println("in stop")
      if (this.synthMap.contains(event.callerID)) {
        //println("freeing synth with id " + event.callerID)
        val synth = this.synthMap(event.callerID)
        this.synthMap -= event.callerID
        synth.free 
      }    
      else {println("synth does not exist")}
      //println(this.synthMap.toString)
    }
  }
  
  
  def act() { 
    while (true) {
      receive {
        case event: PlayAudioEvent => {
          //println("received audio event: "+ event)
          this.play(event)
        }
        case event: StopAudioEvent => {
          //println("received audio event: "+ event)
          this.stop(event)
        }
        case otherEvent => {}
      }
    }
  }
  
  
  def updateParameters(synth: Synth, event: PlayAudioEvent) = {
    this.timbreSpace.updateParameters(synth, event.x, event.y, event.pitch, event.volume)
    /*
    synth.parameters() = ("x" -> event.x);
    synth.parameters() = ("y" -> event.y); 
    synth.parameters() = ("parameterizedPitch" -> event.pitch);
    synth.parameters() = ("parameterizedVolume" -> event.volume);*/
  }
                                               
}

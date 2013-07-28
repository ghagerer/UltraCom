package ui.audio

import scala.collection.mutable._

object PlayAudioEvent {
  def apply(callerID: Int, x: Float, y: Float, pitch: Float, volume: Float, channels: Array[Int]) = {
    new PlayAudioEvent(callerID, x, y, pitch, volume, channels)
  }
}

class PlayAudioEvent(callerID: Int, val x: Float, val y: Float, val pitch: Float, val volume: Float, val channels: Array[Int]) extends AudioEvent(callerID) {
  
  override def toString = {
    "(" + callerID + ", " + x + ", " + y + ", " + pitch + ", " + volume + ", " + (channels: ArrayOps[Int]).toString() + ")"
  }
  
}

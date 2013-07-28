package ui.menus.main

import org.mt4j.Application

import org.mt4j.components.TransformSpace
import org.mt4j.components.MTComponent
import org.mt4j.components.visibleComponents.shapes.MTEllipse
import org.mt4j.util.MT4jSettings

import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor 
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent
import org.mt4j.input.inputProcessors.IGestureEventListener
import org.mt4j.input.inputProcessors.MTGestureEvent

import org.mt4j.util.Color
import org.mt4j.util.math.Vector3D
import org.mt4j.util.math.Vertex
import org.mt4j.types.{Vec3d}

import processing.core.PGraphics

import ui.menus._
import ui.paths.types._
import ui._
import ui.persistence._

object SaveProjectButton {
  
  def apply(app: Application, menu: Menu, center: Vector3D) = {
      new SaveProjectButton(app, menu, center)
  }  
 
  
}


class SaveProjectButton(app: Application, menu: Menu, center: Vector3D) extends Button(app, menu, center) {

  this.setTexture(Ui.loadImage(MT4jSettings.getInstance.getDefaultImagesPath + "save.png"))
  
  override def drawComponent(g: PGraphics) = {
    super.drawComponent(g)
  }  
  
  override def clicked() = {
    super.clicked()
    ProjectFileWriter.save()
  }
  
  override def up() = {
    super.up()
  }
  
  override def down() = {
    super.down() 
  }
  
}

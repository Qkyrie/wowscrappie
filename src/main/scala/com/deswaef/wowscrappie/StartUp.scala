package com.deswaef.wowscrappie

import com.deswaef.weakauras.WeakAuras
import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.system.ApplicationPidFileWriter

/**
 * Created by QuintenDes on 29/07/15.
 */
object StartUp extends App {
  val springApplication: SpringApplication = new SpringApplication(classOf[WeakAuras])
  springApplication.addListeners(new ApplicationPidFileWriter)
  springApplication.run()
}

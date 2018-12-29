package com.simudyne.platform

import java.io.{BufferedWriter, File, FileWriter}
import java.time.LocalDateTime

trait AgentReportGenerator {
  def generate(lines: List[String]) = {
    val time = LocalDateTime.now()
    val file = new File(s"${time.toString}-report.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    lines foreach bw.write
    bw.flush()
    bw.close()
  }
}

object AgentReportGenerator extends AgentReportGenerator

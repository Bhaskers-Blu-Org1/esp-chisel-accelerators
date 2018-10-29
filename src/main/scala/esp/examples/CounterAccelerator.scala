// Copyright 2018 IBM
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package esp.examples

import chisel3._
import chisel3.util.Counter

import esp.Accelerator

/** An ESP accelerator that is done a parameterized number of clock ticks in the future
  * @param ticks the number of clock ticks until done
  */
class CounterAccelerator(val ticks: Int) extends Accelerator {
  val enabled = RegInit(false.B)

  val (_, fire) = Counter(enabled, ticks)
  io.done := fire

  when (io.conf.valid) { enabled := true.B  }
  when (fire)          { enabled := false.B }
}
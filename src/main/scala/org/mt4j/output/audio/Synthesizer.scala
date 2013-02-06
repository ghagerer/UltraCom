/*
 ++1>>  This source code is licensed as GPLv3 if not stated otherwise.
    >>  NO responsibility taken for ANY harm, damage done
    >>  to you, your data, animals, etc.
    >>
  +2>>
    >>  Last modified:  2012 - 4 - 20 :: 8 : 15
    >>  Origin: mt4j (project) / mt4j_mod (module)
    >>
  +3>>
    >>  Copyright (c) 2012:
    >>
    >>     |             |     |
    >>     |    ,---.,---|,---.|---.
    >>     |    |   ||   |`---.|   |
    >>     `---'`---'`---'`---'`---'
    >>                    // Niklas Klügel
    >>
  +4>>
    >>  Made in Bavaria by fat little elves - since 1983.
 */

package org.mt4j.output.audio

import org.lodsb.reakt.async.{ValA}
import de.sciss.synth.{ControlSetMap, Synth}
import org.lodsb.reakt.sync.VarS

class Synthesizer(val synth: Synth) {
	val amplitude: ValA[Float] = new ValA[Float](0f)
	val parameters: VarS[ControlSetMap] = new VarS[ControlSetMap](("_"->0.0));

	parameters.observe({ x => synth.set(x); true;})

}



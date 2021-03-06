/***********************************************************************
 * mt4j Copyright (c) 2008 - 2010 Christopher Ruff, Fraunhofer-Gesellschaft All rights reserved.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 ***********************************************************************/
package org.mt4j.sceneManagement.transition;

import org.mt4j.MTApplication;
import org.mt4j.input.inputProcessors.globalProcessors.AbstractGlobalInputProcessor;
import org.mt4j.sceneManagement.SimpleAbstractScene;

/**
 * The Class SimpleAbstractTransition.
 *
 * @author Christopher Ruff
 */
public abstract class SimpleAbstractTransition extends SimpleAbstractScene implements ITransition {
//	private Iscene previousScene;
//	private Iscene nextScene;

    /**
     * Instantiates a new abstract transition.
     *
     * @param mtApplication the mt application
     * @param name          the name
     */
    public SimpleAbstractTransition(MTApplication mtApplication, String name) {
        super(mtApplication, name);

        //Remove all global input processors - we dont want the transition to respond to input
        AbstractGlobalInputProcessor[] inputProcessors = this.getGlobalInputProcessors();
        for (AbstractGlobalInputProcessor abstractGlobalInputProcessor : inputProcessors) {
            this.unregisterGlobalInputProcessor(abstractGlobalInputProcessor);
        }
        //		this.previousScene = previousScene;
        //		this.nextScene = nextScene;
    }

    /* (non-Javadoc)
      * @see org.mt4j.sceneManagement.SimpleAbstractScene#registerDefaultGlobalInputProcessors()
      */
    @Override
    protected void registerDefaultGlobalInputProcessors() {
    } //DONT REGISTER INPUT PROCESSORS!

    /* (non-Javadoc)
      * @see org.mt4j.sceneManagement.SimpleAbstractScene#init()
      */
    @Override
    public void init() {
    }

//	@Override
//	public void shutDown() {
//	}

//	public Iscene getNextScene() {
//		return this.nextScene;
//	}
//
//	public Iscene getPreviousScene() {
//		return this.previousScene;
//	}


}

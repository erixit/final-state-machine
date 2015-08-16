package com.erixit.fsm;

public enum States implements State {
		    Init {
		        @Override
		        public State next(Input word) {
		            switch(word.read()) {
		                case 'a': return A;
		                default: return Fail;
		            }
		        }
		    },
		    A {
		        @Override
		        public State next(Input word) {
		            switch(word.read()) {
		                case 'a': return A;
		                case 'b': return B;
		                case 'c': return C;
		                case '.': return null;
		                default: return Fail;
		            }
		        }
		    },
		    B {
		        @Override
		        public State next(Input word) {
		            switch(word.read()) {
		                case 'b': return B;
		                case 'c': return C;
		                case '.': return null;
		                default: return Fail;
		            }
		        }
		    },
		    C {
		        @Override
		        public State next(Input word) {
		            switch(word.read()) {
		                case 'c': return C;
		                case '.': return null;
		                default: return Fail;
		            }
		        }
		    },
		    Fail {
		        @Override
		        public State next(Input word) {
		               return Fail;
		        }
		    };
		  
		    public abstract State next(Input word);

}

package simplejseprograms;


// used javac version 11.0.13
import java.util.Arrays;
import java.util.Scanner;

public class SimpleReversiGame {
	
// for board	
String[][] board= new String[][]{{}};
// for white piece
String whiteDot = "◯";
// for left piece
String blackDot ="⬤";
// in order to decide whose turn 0 for white 1 for black
boolean whiteTurn = true;
// let both players have right legal moves at init state
boolean hasWhiteLeagalMoves = true;
boolean hasBlackLeagalMoves = true;

	
public static void main(String[] args) {
	 SimpleReversiGame game = new SimpleReversiGame();

	game.initializeGameBoard();
}


 void initializeGameBoard() {
	 System.out.println("Game has started");
	 
	 // simply initialized the board
	board = new String[] []{
			{" ","a","b","c","d","e","f","g","h"},
			{"1","_","_","_","_","_","_","_","_"}, 
			{"2","_","_","_","_","_","_","_","_"}, 
			{"3","_","_","_","_","_","_","_","_"},
			{"4","_","_","_",this.whiteDot,this.blackDot,"_","_","_"},
			{"5","_","_","_",this.blackDot,this.whiteDot,"_","_","_"},
			{"6","_","_","_","_","_","_","_","_"},
			{"7","_","_","_","_","_","_","_","_"},
			{"8","_","_","_","_","_","_","_","_"},
			};
			// build the game
			this.buildGame();
			
}


 void buildGame(){
	 // show board
	 this.viewBoard();
	 // check left legal moves if yes then make move else show no legal moves left for current player
	if( this.checkLeftMoves()) {
		 this.makeMove();
	}else {
		if(this.hasBlackLeagalMoves || this.hasWhiteLeagalMoves) {
			System.out.println("There are no leagal moves for the current player");
			
			this.whiteTurn = !this.whiteTurn;
			this.buildGame();
		}else {
			this.showWinning();
		}
		
	}
	

	 
}
 
 
 
 void showWinning() {
	 int blackPoint = 0;
	 int whitePoint = 0;
	 for(int x = 0 ; x < this.board.length ; x++) {
		 for(int y = 0 ; y < this.board[x].length ; y++) {
			 if(this.board[x][y] == this.blackDot) {
				 blackPoint = blackPoint+1;
			 }
			 if(this.board[x][y] == this.whiteDot) {
				 whitePoint = whitePoint + 1;
			 }
		 }
	 }
	 System.out.println("Result:");
	 System.out.println("Black:"+ blackPoint+"Pieces");
	 System.out.println("White:"+ whitePoint+"Pieces");
	 if(blackPoint > whitePoint) {
		 System.out.println("Black wins and the game is over");
	 }
	 else if(whitePoint > blackPoint) {
		 System.out.println("White wins and the game is over");
	 }else {
		 System.out.println("Game is draw");
	 }
 }
 
 void viewBoard(){
	 for(int i = 0 ; i < board.length ; i ++) {
			for(int x = 0 ; x < board[i].length ; x++) {
				System.out.print(board[i][x] + " ");
			}
			System.out.println(" ");
		}

 }
 
 
 void makeMove() {
	 if(this.whiteTurn) {
		 System.out.println("Its white player's turn");
	 }else {
	    System.out.println("Its black player's turn");
	 }
	 System.out.println("Input the position of piece:<>");
	 Scanner sc = new Scanner(System.in);
	 String input = sc.nextLine();
	 if(input.length() != 2) {
		this.detectedInvalidMove();
	 }else {
		 try {
	
		
		
		 int x= Integer.parseInt(String.valueOf(input.charAt(1)));
		 int y = this.parseCharToInt(input.charAt(0));
		 if((x >= 1 && x <= 8 ) && (y >= 1 && y <= 8)) {
			 // check valid move
		     if(this.checkLeagalMove(x, y)) {
		    	 // check and move piece
		    	 this.checkMove(x , y);
				 this.whiteTurn = !this.whiteTurn;
				 this.buildGame();
		     }else {
		    	 // to show illegal moves
		    	 this.detectedInvalidMove();
		     }
			
			 
		 }else {
			 this.detectedInvalidMove();
			
		 }
			

		 }
	 catch(Exception e) {
		 
		 System.out.println(e);
	 }
	 }
 }
 
 void detectedInvalidMove() {
	 System.out.println("This Position is Illegal");
	 this.buildGame();
 }
 
 
 boolean checkLeagalMove(int x, int y) {
	 if(this.board[x][y] == "_" && (this.checkVertical(x, y,false) || this.checkHorizontal(x, y, false) || this.checkDiagonal(x, y, false) )){
		 return true;
	 }
	 return false;
 }
 
 
 boolean checkLeftMoves() {
		for(int x = 0 ; x < this.board.length ; x++) {
			for(int y = 0 ; y < this.board[x].length ; y++) {
				if(this.board[x][y] == "_") {
					if(this.checkHorizontal(x, y, false) || this.checkVertical(x, y, false)|| this.checkDiagonal(x, y, false)) {
					    if(this.whiteTurn) {
					    	this.hasWhiteLeagalMoves = true;
					    }else {
					    	this.hasBlackLeagalMoves = true;
					    }
						return true;
					}
				}
			}
		}
		  if(this.whiteTurn) {
		    	this.hasWhiteLeagalMoves = false;
		    }else {
		    	this.hasBlackLeagalMoves = false;
		    }
		return false;
	}

void checkMove(int x , int y){
	// for horizontal axis
				this.checkHorizontal(x ,y , true);
   // for vertical axis
				this.checkVertical(x, y, true);
  // for diagonal axis
    		   this.checkDiagonal(x , y, true);

}



boolean checkVertical(int x, int y, boolean putPiece){
	boolean result1 = false;
	boolean result2 = false;
	if(x - 2 >= 1) {
		result1 = this.checkVerticalUpper(x, y, putPiece);
	}
	if(x + 2 <= 8) {
	    result2 = this.checkVerticalLower(x, y, putPiece);
	}
	if(result1 || result2) {
		return true;
	}else {
		return false;
	}

}

boolean checkHorizontal(int x , int y, boolean putPiece) {
	boolean result1 = false;
	boolean result2 = false;
	if(y - 2 >= 1) {
		result1 = this.checkHorizontalLeft(x, y, putPiece);
	}
	if(y + 2 <= 8) {
		result2 = this.checkHorizontalRight(x, y, putPiece);
	}
	if(result1 || result2) {
	
		return true;
	
	}else {
		return false;
	}
	
}

boolean checkDiagonal(int x, int y, boolean putPiece) {
	boolean result1 = false;
	boolean result2 = false;
	boolean result3 = false;
	boolean result4 = false;
	
	// for upper left
	if(x - 2 >= 1 && y -2 >= 1) {
		result1 = this.checkDiagonalUpperLeft(x, y, putPiece);
	}
	if(x -2 >= 1 && y +2 <= 8 ) {
	   result2 =	this.checkDiagonalUpperRight(x, y, putPiece);
	}
	if(x + 2 <= 8 && y -2 >= 1) {
		result3 = this.checkDiagonalLowerLeft(x, y, putPiece);
	}
	if(x+2 <= 8 && y+2 <= 8) {
		result4 = this.checkDiagonalLowerRight(x, y, putPiece);
	}
	if(result1 || result2 || result3 || result4) {
	
		return true;
	}else {
		return false;
	}
	
}

boolean checkVerticalUpper(int x , int y, boolean putPiece) {
	int x0 = x;
	x = x - 1;
	// for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x -1;
			while(x >= 1) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y] = this.whiteDot;
						this.eatVertical(x0, x, y);
					}
			       
//					break;
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x-1;
			}
		}
	}
	// for black turn
	else {
		if(this.board[x][y] == this.whiteDot) {
			x = x -1;
			while(x >= 1) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y] = this.blackDot;
						this.eatVertical(x0, x, y);
					}
					return true;
					
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x -1;
			}
		}
	}
	return false;
}


boolean checkVerticalLower(int x, int y,boolean putPiece) {
	int x0 = x;
	x = x + 1;
	// for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x +1;
			while(x <= 8) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y] = this.whiteDot;
						this.eatVertical(x0, x, y);
					}
				
				return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x+1;
			}
		}
	}
	// for black turn
	else {
		if(this.board[x][y] == this.whiteDot) {
			x = x +1;
			while(x <= 8) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y] = this.blackDot;
						this.eatVertical(x0, x, y);
					}
					
                    return true;
				
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x +1;
			}
		}
	}
	return false;
}
boolean checkHorizontalRight(int x,int y, boolean putPiece) {
	   int y0 = y;
       y = y +1;

	 if(this.whiteTurn) {
		 // for white turn
		 if(this.board[x][y] == this.blackDot) {
			 y = y+1;
				while(y <= 8) {
					if(this.board[x][y] == this.whiteDot) {
						if(putPiece) {
							this.board[x][y0] = this.whiteDot;
							this.eatHorizontal(y0, y, x);
						}
						
				      return true;
						
					}
					if(this.board[x][y] == "_") {
						return false;
					}
					y = y +1;
				}
			}
		 
	 }else {
		 // for black turn
		 if(this.board[x][y] == this.whiteDot) {
			 y = y+1;
				while(y <= 8) {
					if(this.board[x][y] == this.blackDot) {
						if(putPiece) {
							this.board[x][y0] = this.blackDot;
							this.eatHorizontal(y0, y, x);
						}
						return true;
						
					}
					if(this.board[x][y] == "_") {
						return false;
					}
					y = y + 1;
				}
			}
	 }
	return false;
}

boolean checkHorizontalLeft(int x , int y, boolean putPiece) {
	   int y0 = y;
       y = y -1;

	 if(this.whiteTurn) {
		 // for white turn
		 if(this.board[x][y] == this.blackDot) {
			 y = y-1;
				while(y >= 1) {
					if(this.board[x][y] == this.whiteDot) {
						if(putPiece) {
							this.board[x][y0] = this.whiteDot;
							this.eatHorizontal(y0, y, x);
						}
					
				       return true;
						
					}
					if(this.board[x][y] == "_") {
						return false;
					}
					y = y-1;
				}
			}
		 
	 }else {
		 // for black turn
		 if(this.board[x][y] == this.whiteDot) {
			 y = y-1;
				while(y >= 1) {
					if(this.board[x][y] == this.blackDot) {
						if(putPiece) {
							this.board[x][y0] = this.blackDot;
							this.eatHorizontal(y0, y, x);
						}
					
					return true;
					}
					if(this.board[x][y] == "_") {
						return false;
					}
					y = y-1;
				}
			}
	 }
	 return false;
	
 
}



void eatHorizontal(int y0 , int y, int x){
if(y0 > y) {
	// for left horizontal eating
	for(int i = y0 -1 ; i > y ; i--) {
		
		this.board[x][i]= (this.whiteTurn)? this.whiteDot : this.blackDot;
	}
	
}else {
	// for right horizontal eating
	for(int i = y0 + 1; i < y ; i++) {
		this.board[x][i]= (this.whiteTurn)? this.whiteDot : this.blackDot;
	}
}
}

void eatVertical(int x0 , int x , int y) {

	if(x0 > x) {
		// for upper vertical eating
		for(int i = x0 -1 ; i > x ; i--) {
			
			this.board[i][y]= (this.whiteTurn)? this.whiteDot : this.blackDot;
		}
		
	}else {
		// for lower horizontal eating
		for(int i = x0 + 1; i < x ; i++) {
			this.board[i][y]= (this.whiteTurn)? this.whiteDot : this.blackDot;
		}
	}
}

void eatDiagonal(int x0 , int x , int y0 , int y) {
	// for upperleft eating
	if(x0 > x && y0 > y) {
		x0 = x0 -1;
		y0 = y0-1;
		while(x0 > x && y0 > y) {
			this.board[x0][y0] = this.whiteTurn ? this.whiteDot : this.blackDot;
			x0 = x0 -1;
			y0 = y0 -1;
		}
	}
	// for upperright eating
	if(x0 > x && y0 < y) {
		x0 = x0 - 1;
		y0 = y0 + 1;
		while(x0 > x && y0 < y) {
			this.board[x0][y0] = this.whiteTurn ? this.whiteDot : this.blackDot;
			x0 = x0 - 1;
			y0 = y0 + 1;
		}
	}
	
	// for lower left eating
	if(x0 < x && y0 > y) {
		x0 = x0 + 1;
		y0 = y0 -1;
		while(x0 < x && y0 > y) {
			this.board[x0][y0] = this.whiteTurn ? this.whiteDot : this.blackDot;
			x0 = x0 + 1;
			y0 = y0 -1;
		}
	}
	
	if(x0 < x && y0 < y) {
		x0 = x0+1;
		y0 = y0+1;
		while(x0 < x && y0 < y) {
			this.board[x0][y0] = this.whiteTurn ? this.whiteDot : this.blackDot;
			x0 = x0+1;
			y0 = y0 + 1;
		}
	}
		
	
}


boolean checkDiagonalUpperLeft(int x, int y, boolean putPiece) {
	int x0 = x;
	int y0 = y;
	x = x -1 ;
	y = y -1;
	//for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x -1 ;
			y = y-1;
			while(x >= 1 && y >= 1) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y0] = this.whiteDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x -1 ;
				y = y-1;
				
			}
		}
	}
	// for black turn
	else {
		if(this.board[x][y] == this.whiteDot) {
			x = x -1 ;
			y = y-1;
			while(x >= 1 && y >= 1) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y0] = this.blackDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x -1 ;
				y = y-1;
				
			}
		}
	}
	return false;
}

boolean checkDiagonalUpperRight(int x, int y, boolean putPiece) {
	int x0 = x;
	int y0 = y;
	x = x -1 ;
	y = y +1;
	//for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x -1 ;
			y = y+1;
			while(x >= 1 && y <= 8) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y0] = this.whiteDot;
						this.eatDiagonal(x0, x, y0, y);
					}
			
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x -1 ;
				y = y+1;
			}
		}
	}
	// for black turn
	else {
		
		if(this.board[x][y] == this.whiteDot) {
			x = x -1 ;
			y = y+1;
			while(x >= 1 && y <= 8) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y0] = this.blackDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x -1 ;
				y = y+1;
			}
		}
	}
	return false;
}

boolean checkDiagonalLowerLeft(int x, int y, boolean putPiece) {
	int x0 = x;
	int y0 = y;
	x = x +1 ;
	y = y - 1;
	//for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x +1 ;
			y = y- 1;
			while(x <= 8 && y >= 1) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y0] = this.whiteDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x +1 ;
				y = y- 1;
			}
		}
	}
	// for black turn
	else {
		if(this.board[x][y] == this.whiteDot) {
			x = x +1 ;
			y = y- 1;
			while(x <= 8 && y >= 1) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y0] = this.blackDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x +1 ;
				y = y- 1;
			}
		}
	}
	return false;
}

boolean checkDiagonalLowerRight(int x, int y, boolean putPiece) {
	int x0 = x;
	int y0 = y;
	x = x +1 ;
	y = y + 1;
	//for white turn
	if(this.whiteTurn) {
		if(this.board[x][y] == this.blackDot) {
			x = x +1 ;
			y = y + 1;
			while(x <= 8 && y <= 8) {
				if(this.board[x][y] == this.whiteDot) {
					if(putPiece) {
						this.board[x0][y0] = this.whiteDot;
						this.eatDiagonal(x0, x, y0, y);
					}
					
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x +1 ;
				y = y + 1;
			}
		}
	}
	// for black turn
	else {
		if(this.board[x][y] == this.whiteDot) {
			x = x +1 ;
			y = y + 1;
			while(x <= 8 && y <= 8) {
				if(this.board[x][y] == this.blackDot) {
					if(putPiece) {
						this.board[x0][y0] = this.blackDot;
						this.eatDiagonal(x0, x, y0, y);
					}
			
					return true;
				}
				if(this.board[x][y] == "_") {
					return false;
				}
				x = x +1 ;
				y = y + 1;
			}
		}
	}
	return false;
}




int parseCharToInt(char c) {
	switch(c) {
	case 'a':{
		return 1;
		
	}
	case 'b': {
		return 2;
	}
	case 'c': {
		return 3;
	}
	case 'd': {
		return 4;
	}
	case 'e': {
		return 5;
	}
	case 'f': {
		return 6;
	}
	case 'g': {
		return 7;
	}
	case 'h' : {
		return 8;
	}
	default: {
		return 0;
	}
	
	}
	
}

}


# kalah-game-spring-rest
Implementation of kalah game (https://en.wikipedia.org/wiki/Kalah) server using spring boot restful services.
Game by default will play with 6 stones per pit, can be changed.


# usage

   `mvn install spring-boot:run`
    
    After server is started endpoints will be available:
    
    POST
    http://localhost:8080/games
    
    PUT
    http://localhost:8080/games/{gameId}/pits/{pitId}
    
 # game example   
    

    Create game:  
    POST to http://localhost:8080/games
    returned value will have generated id of game and url
    
    {
    	"id": "1",
    	"url": "http://127.0.1.1:8080"
    }
    
    Play player 1 move stones from pit 3 : 
    PUT http://localhost:8080/games/1/pits/3
    returned value 
    {
    	"id": "1",
    	"url": "http://127.0.1.1:8080",
    	"status": {
    		"11": "6",
    		"12": "6",
    		"13": "6",
    		"14": "0",
    		"1": "6",
    		"2": "6",
    		"3": "0",
    		"4": "7",
    		"5": "7",
    		"6": "7",
    		"7": "1",
    		"8": "7",
    		"9": "7",
    		"10": "6"
    	}
    }
    
    Play player 2, move stones from pit 8:
    PUT http://localhost:8080/games/1/pits/8
    returned value
    {
    	"id": "1",
    	"url": "http://127.0.1.1:8080",
    	"status": {
    		"11": "7",
    		"12": "7",
    		"13": "7",
    		"14": "1",
    		"1": "7",
    		"2": "6",
    		"3": "0",
    		"4": "7",
    		"5": "7",
    		"6": "7",
    		"7": "1",
    		"8": "0",
    		"9": "8",
    		"10": "7"
    	}
    }
    


      

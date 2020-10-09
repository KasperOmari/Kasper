#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>

char response[1024];
char Message[1024];

int check(char *msg){
        return !strcmp(msg,"exit");
}
int main(){
        //creat a socket
        int soc;
        soc = socket(AF_INET, SOCK_STREAM, 0);
	
	//specify an address for the socket
	struct sockaddr_in server_address;
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(9002);
	server_address.sin_addr.s_addr = INADDR_ANY;
	int con = connect(soc , (struct sockaddr *) &server_address , sizeof(server_address));
	
	//check for error with the connection
	if(con == -1){
		puts("Connection Error !");
		close(soc);
		exit(0);
	}

	//recieve data from the server
	recv(soc, &response, sizeof(response), 0);

	//print recieved data
	printf("Server : %s\n",response);	

	//Send message to server
	//send(soc, Message, strlen(Message), 0);
	while(1){
		printf("Message For The Server : ");
		//scanf("%s",Message);
		gets(Message);
		send(soc, Message, strlen(Message), 0);
		//fflush(stdout);
		if(check(Message)){
			puts("Disconnected !");
			break;
		}
	}
	//close the socket
	close(soc);
        return 0;

}



#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>

int read_size;
char Message[1024]="Hello client , I'm the Server";

int check(char *msg){
	return !strcmp(msg,"exit");
}
int main(){
	//creat socket
	int soc;
	soc = socket(AF_INET, SOCK_STREAM, 0);
	
	//define server address
	struct sockaddr_in server_address;
	server_address.sin_family = AF_INET;
	server_address.sin_port = htons(9002);
	server_address.sin_addr.s_addr = INADDR_ANY;

	//bind the socket to ip and port
	bind(soc, (struct sockaddr*) &server_address, sizeof(server_address));
	
	listen(soc, 5);
	
	puts("Listening...");

	int client_soc;
	client_soc = accept(soc, 0, 0);
	//send the message
	send(client_soc, Message, sizeof(Message), 0);
	if(client_soc<0){
		puts("Connection Error");
		return 0;
	}	
	puts("Connected");
	
	//Receive a message from client
	while(1){
		char response[1024]="";
		recv(client_soc,response, 1024, 0);
		//fflush(stdout);
		
		if(check(response)){
                        puts("Disconnected !");
                        break;
                }
		
		strcat(response," > out.txt");
		int sys = system(response);
		printf("%s\n",stdout);
		if(sys != 0)//worng command enterd
			printf("Worng Command");
			

	}
	//close the sockets
	close(client_soc);
	close(soc);
	return 0;
}

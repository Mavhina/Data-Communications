# This practical will focus on creating a web server. Handling of client connections must be multi-threaded.

important network technology and protocol suite: the web and HTTP. For your practical, 
# you need to create a basic web server using official HTTP protocol
Create a Java application which acts as a web server. Create a server socket which listens on
port 4321. Accept any client connections and establish the needed streams for input/output.
Any client requests should be processed according to HTTP. Test the web server with a
web browser (Opera, Firefox, Chrome) on localhost. Use the appropriate response codes for
requests:
200 When a request can be served without issue (must be able to handle binary files).
404 When a requested page/content cannot be found.
500 When an error occurs.
Different pages can be requested by the client. Based on the additional data files that have
been provided to you, your server should be able to handle the following:
 localhost:4321/Afrikaans should serve the Afrikaans.html file to the client.
 localhost:4321/Zulu should serve the Zulu.html file to the client.
 localhost:4321/ZuluWithImage should serve the ZuluWithImage.html file to the client.
 localhost:4321/Africa.jpg should serve the Africa.jpg file to the client.
# Bonus
1. Add to your web server the ability to serve a video streaming page that uses HTML5
or JavaScript to stream the sample video from additional files on Eve with the ablity to
play, pause, seek and choose an alternate source video url. You will have to create your
own html page for the bonus. (Please note: DO NOT include the sample video file in
your final submission)

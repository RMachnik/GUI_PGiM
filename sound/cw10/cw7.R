install.packages('seewave')
library(seewave)
library(tuneR)
library(audio) 
	s1<-readWave("c:\\Users\\SG0219139\\Documents\\Studia\\Dropbox\\Semestr VII\\Przetwarzanie grafiki i muzyki\\GUI\\sound\\cw10\\nagranieGlosu.wav")
	play(s1)
	print(s1)
	if (){
		
	}	
	else{
		
	}
	oscillo(s1,f=8000)
	data1 = s1@left
	plot(data1, type='l', lwd=2, col="black")
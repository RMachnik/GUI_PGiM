install.packages('seewave')
library(seewave)
library(tuneR)
library(audio) 
t = seq(0, 3, 1/8000)  
t1 = seq(0, 3, 1/7000)    #times in seconds if sample for 3 seconds at 8000Hz 
u = (2^15-1)*sin(2*pi*600*t)    #440 Hz sine wave that lasts t length seconds (here, 3 seconds) 
u1 = (2^15-1)*sin(2*pi*600*t1) 

w = Wave(u, samp.rate = 8000, bit=16) #make the wave variable
w1 = Wave(u1,samp.rate=8000,bit=16) 
play(w)
play(w1)
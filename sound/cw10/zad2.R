install.packages('seewave')
library(seewave)
library(tuneR)
library(audio)
t = seq(0, 3, 1/8000)
u = (2^15-1)*sin(2*pi*440*t)
plot.ts( u, ylim=c(-2,2), main="sin")
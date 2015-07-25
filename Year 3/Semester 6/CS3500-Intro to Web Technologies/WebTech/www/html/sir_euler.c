#include <stdio.h> 
#include <stdlib.h> 

int main(int argc, char **argv) {
double    s= atof(argv[1]);      // susceptible part of populaton
double    i= atof(argv[2]);       // infected part of population
double    r= atof(argv[3]);       // recovered (immune) part of population
double    total=s+i+r; // total population, constant
double    beta = atof(argv[4]); // contact rate
double    gamma = atof(argv[5]); // recovery rate
int       time = 0;    // current time (perhaps weeks below)
int       timeFinal = 100; // final time
int	  stepsPerTime = 10; // Euler steps for each time unit
double    dt = 1.0/(double)stepsPerTime; // for each Euler step.
int       j;           // counter for euler stps
double    c;           // corection for Euler tuncation error

printf("%10.6f,%10.6f,%10.6f,", s, i, r);

for(time=0; time < timeFinal; time++) {
  for(j=0; j<stepsPerTime; j++) {
    // Take stepsPerTime to reduce truncation error
    // time_actual  = time + i*dt;
    s = s - beta*s*i*dt;
    i = i + (beta*s*i -gamma*i)*dt;
    r = r + gamma*i*dt;
    c = (s+i+r) / total; 
    // force s + I + r  == total to compensate for the 
    // truncation error in Euler's method.
    if( c == 0.0) {
      fprintf(stderr, "Error total at time = %d j = %d is 0.0\n", time, j);
      exit(1);
    }
    s = s/c;
    i = i/c;
    r = r/c;
  }  
  printf("%10.6f,%10.6f,%10.6f,", s, i, r);
}

return 0;
}



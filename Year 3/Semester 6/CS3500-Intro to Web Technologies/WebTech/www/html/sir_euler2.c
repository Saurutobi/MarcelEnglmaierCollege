// Euler's metod for the SIR model.
//
// Reference SIR epidemuic model:
//    http://en.wikipedia.org/wiki/Compartmental_models_in_epidemiology
// Reference Euler's metod:
//   http://en.wikipedia.org/wiki/Euler%27s_method
// Refenence Runge Kutta method
//   http://en.wikipedia.org/wiki/Runge_Kutta
//
// The error in Euler's method will result in  S+I+R changing over
// time because of following the tangent. This is called truncation error
// and is much greater than any round off error here.
//
// One way to reduct teh round off error is to take xeveral Euler steps
// for each time step (stepsPerTime = 10; - below), but the trunaction
// error would still be large. 
//
// Adjusting the total = s + i + r at each time step to equal the
// orginal total is forced below. Imposing such conservation 
// laws (energy, momentium, total population, ...) is typical
// in solving dynamical system.
//
// Going to a better method would be needed in practice.
// The Runge Kutta method RK45 is "almost" as easy, much more accurate, 
// but I'd still // force the sum to remain constant at each step.
//
// There are more advanced solvers that can control the error 
// and automatically adapt the time step as needed.
// Systems of thousands on equations and unknowns are used routinely
//
// If s + i + r == 100.0, these can be thought of as percents.  
// Note if they are not percents we have fractons of a person 
// in the three groups.  
//
#include <stdio.h> 
#include <stdlib.h> 

int main(int argc, char **argv) {
double    s= atof(argv[1]);      // susceptible part of populaton
double    i= atof(argv[2]);       // infected part of population
double    r= atof(argv[3]);       // recovered (immune) part of population
double    total=s+i+r; // total population, constant
double    beta = atof(argv[4]); // contact rate
double    gamma = atof(argv[5]); // recovery rate
double       dT = atof(argv[6]);    // current time (perhaps weeks below)
int       timeFinal = atof(argv[7]); // final time
double time;
double Sn = (s/total);
double In = (i/total);
double Rn = (r/total);
double Sprev = Sn;
double Iprev = In;
double Rprev = Rn;

printf("%10.6f,%10.6f,%10.6f,",s,i,r);

for(time=1; time < timeFinal; time+=dT) {
	Sn = (Sprev - (beta*Sprev*Iprev*dT));
	In = (Iprev + ((beta*Sprev*Iprev - (gamma*Iprev))*dT));
	Rn = (Rprev + (gamma*Iprev*dT));
	Sprev = Sn;
	Iprev = In;
	Rprev = Rn;
	s = (total * Sn);
	i = (total * In);
	r = (total * Rn);
	printf("%10.6f,%10.6f,%10.6f,",s,i,r);
}  



return 0;
}



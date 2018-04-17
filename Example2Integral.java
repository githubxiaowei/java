@FunctionalInterface
interface Fun { double fun( double x );}

public class Example2Integral
{
	public static void main(String[] args)
	{
		double d = Integral( new Fun(){
			public double fun(double x){ 
				return Math.sin(x); 
			}
		}, 0, Math.PI, 1e-5 );

		d = Integral( x->Math.sin(x),
			0, Math.PI, 1e-5 );
		System.out.println( d );

		d = Integral( x->x*x, 0, 1, 1e-5 );
		System.out.println( d );

	}

	static double Integral(Fun f, double a, double b, double eps)// 积分计算
	{
		int n,k;
		double fa,fb,h,t1,p,s,x,t=0;

		fa=f.fun(a); 
		fb=f.fun(b);

		// 迭代初值
		n=1; 
		h=b-a;
		t1=h*(fa+fb)/2.0;
		p=Double.MAX_VALUE;

		// 迭代计算
		while (p>=eps)
		{ 
			s=0.0;
			for (k=0;k<=n-1;k++)
			{ 
				x=a+(k+0.5)*h;
				s=s+f.fun(x);
			}

			t=(t1+h*s)/2.0;
			p=Math.abs(t1-t);
			t1=t; 
			n=n+n; 
			h=h/2.0;
		}
		return t;
	}

}
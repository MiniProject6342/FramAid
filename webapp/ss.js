/**
 * 
*/
var a=[1,2,3,4,5];
var c=[],d=[];
var s=0;
function odd(arr,call){
	for(ele of arr){
		if(ele%2!=0){
			call(ele);
		}
		else{
			d.push(ele);
		}
	}
	return c;
}
function sum(arr){
	c.push(arr);
}
console.log(odd(a,sum));
console.log(d);

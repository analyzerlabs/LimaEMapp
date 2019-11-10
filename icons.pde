void home(int x, int y , int size){
    int a=3,b=2;
    fill(naranja);
    stroke(naranja);
    triangle(x+a*size,y,x,y+b*size,x+2*a*size,y+b*size);
    rect(x,y+(b+1)*size,2*a*size,2*a*size);
    fill(fondoPrincipal);
    stroke(fondoPrincipal);
    rect(x+2*a*size/3,y+(b+1)*size+a*size,2*a*size/3,2*a*size);
}

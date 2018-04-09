# Number_recognition


We take a picture (e.g. 10x10) composed of black and white pixels.</br>
We transform it into a two-dimensional matrix composed of ones and zeros.</br>
INPUT:</br>
<img src="https://raw.githubusercontent.com/Menober/Number_recognition/master/res/image.bmp" width=50 height=50></a></br>
PROCESSED INPUT: </br>
0 0 0 1 0 0 0 0 0 0 </br>
0 0 1 1 0 0 0 0 0 0</br>
0 0 1 1 0 0 0 0 0 0</br>
0 1 1 1 0 0 0 0 0 0</br>
1 1 0 1 0 0 0 0 0 0</br>
1 0 0 1 0 0 0 0 0 0</br>
0 0 0 1 0 0 0 0 0 0</br>
0 0 0 1 0 0 0 0 0 0</br>
0 0 0 1 0 0 0 0 0 0</br>
0 0 0 1 0 0 0 0 0 0</br>

We compare this processed input with an instance matrix made of a trening data.</br>
We select the set with the smallest difference between matrices.</br>
<img src="https://raw.githubusercontent.com/Menober/Number_recognition/master/res/out/example.png"></a>

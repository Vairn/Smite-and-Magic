; Collision stuff
Dim collmap(16,16,4)
Dim eventmap(16,16)

Function checkcollision(x,y,d)
Return collmap(x,y,d)
End Function

Function fillcollision(mapno)
For m.map=Each map
If m\area=mapno
envion=m\envion
For y=15 To 0 Step -1
For x=0 To 15
a=(y*16)+x
cwall$=Right(Bin(PeekByte(m\collmap,a)),8)
For d=0 To 3
	temp=getwall(cwall,d)
	If temp=1 Or temp=3
		collmap(x,y,d)=0 
	Else
		collmap(x,y,d)=0
	EndIf	
Next
Next
Next
EndIf
Next
End Function

Function checkevent(x,y)
Return eventmap(x,y)
End Function

Function fillevent(mapno)
For m.map=Each map
If m\area=mapno
For y=15 To 0 Step -1
For x=0 To 15
a=(y*16)+x
cwall$=Right(Bin(PeekByte(m\collmap,a)),8)
;For d=0 To 3
	temp=getwall(cwall,0)
	If temp=2 Or temp=3
		eventmap(x,y)=1 
		events=events+1
	Else
		eventmap(x,y)=0
	EndIf	
;Next
Next
Next
EndIf
Next
DebugLog events
End Function
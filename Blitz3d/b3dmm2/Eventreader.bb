; Might and magic 2 event.dat reader 
; todo, read the whole file in properly.

Type eventText
Field ID
Field map
Field Dat$
End Type

Type Eventcode
Field ID
Field MAP
Field Dat$
Field TXTno
End Type
Graphics 640,480,16,2
SetBuffer BackBuffer()
Dim mess$(10)
load_event("event.dat")

viewitems()

Function load_event(file$)
fi=ReadFile(file$)
b= FileSize(file$)/100

c=-1 : ecid=0 : etid=1: whch=0
map=1

For a=0 To FileSize(file$)
Color 255,0,0
Cls
Rect 0,0,204,20,0

If a Mod b=0
c=c+1
Color 255,255,255
Rect 1,1,c*2,18,1
;olor 0,0,255
;Text 100,10,c+"%",1,1
;Color 255,255,255
;Text 350,10,"PROCESSING... Event.dat"
Flip 0
End If

byte=ReadByte(fI)
If byte=255 And am=0
	If whch=0
	 whch=1 

	EndIf
	
		If whch=1
		
		ec.eventcode=New eventcode
		ec\dat$=ch$
		ec\ID=edid
		ec\map=map
		edid=edid+1
		ch$=""
		Else If whch=2

		et.eventtext=New eventtext
		et\dat$=ch$
		et\ID=etid
		etid=etid+1
		et\map=map
		EndIf
		ch$=""
	am=1
Else If byte=255 And am=1
	am=2
	If whch=1 
		whch=2
		ch$=""
	Else
		whch=1
		map=map+1
		etid=1
		edid=1


	EndIf
Else
am=0
EndIf
If byte=0 And lbyte=0 And whch=2 Then whch=0
If whch=1 And byte<>255
	If ch$=""
	ch$=ch$+Right(Hex(byte),2)
	Else 
	ch$=ch$+","+Right(Hex(byte),2)
	EndIf
EndIf
If whch=2 And byte<>255
	ch$=ch$+Chr(byte)
EndIf
If am=0 And lam=1

Else If am=0 And lam=2
EndIf
lbyte=byte
lam=am
Next
ec.eventcode=First eventcode
Delete ec
End Function 

Function Viewitems()
ec.eventcode=First eventcode
et.eventtext=First eventtext

While Not KeyDown(1)
Cls
Color 255,0,0
Text 0,0,"EVENT CODE"
Text 0,100,"EVENT TEXT"

Color 0,255,0
Text 20,20,"MAP:"
Text 20,40,"EVENT NO:"
Text 20,60,"DATA:"
Text 20,120,"MAP:"
Text 20,140,"TEXT NO:"
Text 20,160,"TEXT:"
Color 0,128,255
Text 90,20,ec\map
Text 90,40,Hex(ec\ID)
Text 90,60,ec\dat$
Text 90,120,et\map
Text 90,140,Hex(et\ID)

c=1
For l=1 To Len(et\dat$)
If Mid(et\dat$,l,1)="@"
c=c+1
Else
mess(c)=mess(c)+Mid(et\dat$,l,1)
End If

Next
For a=1 To c
Text 90,140+(20*a),mess(a)
mess(a)=""
Next

If Not ec.eventcode=Last eventcode
If KeyDown(200)
	ec =After  ec
End If
EndIf
If Not ec.eventcode=First eventcode
If KeyHit(208)
	ec =Before  ec
End If
End If
If Not et.eventtext=Last eventtext
If KeyHit(205)
	et =After  et
End If
End If
If Not et.eventtext=First eventtext
If KeyHit(203)

	et =Before et
End If
EndIf



Flip
Wend
End Function
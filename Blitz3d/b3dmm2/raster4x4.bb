; Might and Magic 2 Main source file, this is being rewritten to have the outside as one whole level.
; Copyright 2002 Adam Templeton.
;Include "XLNT-3D_V1.1.bb"
;Include "lpofGUI.bb"
;Include "Start.bb"

;Graphics3D 242,62,16,2

;GUI_GFXSETUP()
;start

Graphics3D 320,240,32,1
Include "smx.bb"
Global timeofday=0
Const pname$="Might and Magic ][ SE "
Const version=0.1
Const segs=1,width#=4,depth#=1.125
AppTitle pname$;,"Exit "+name$
Const TYPE_PLAYER=1,TYPE_WALL=2
Collisions TYPE_PLAYER,TYPE_WALL,2,3

Type Fpsss
	Field time
End Type

Global Timer,OldTimer,Count

Global ocx,ocy,oxz,ocr,ocp,oca
Dim lsin(360)
For a=0 To 360
lsin(a)=Sin(a)
Next

Type map
	Field Area		; area number 0-59
	Field envion	; 0 town, 1=cavern, 2=castle, 3=outside
	Field Name$		; Name of area,ie A1,middlegate.
	Field Exits[4]
	Field mapdata$[256]
	Field collmap$[256] 
	Field roofdata[256] ; 0 empty, 1 roof
	Field events[256] ;=0 no event, 1=event, later the event.dat will be used to give them there proper numbers
	Field meshsurf,roofsurf,collsurf
	Field surfloc[2]
	Field mesh
	Field coll
	Field show=0
	Field roof
	Field flor
End Type
Type event
Field ID
Field map
Field X
Field Y
Field txt$
End Type
Dim temparray(256)
firstfps()
Global camera=CreateCamera()
SetBuffer BackBuffer()
;PositionEntity camera,10,25,-30
;RotateEntity camera,40,-30,0
	CameraRange camera,1,50
	CameraFogRange camera,5,45
CameraFogMode camera,1
;CameraFogColor camera,160,160,255

CameraClsColor camera,0,0,0
CameraViewport camera,0,0,80,60
Dim pcol(160,120)
Dim pred(120,90)
EntityType camera,type_Player
EntityRadius camera,1.6
	light=CreateLight()
	RotateEntity light,0,0,0
;	Collisions TYPE_PLAYER,TYPE_WALL,2,3
	ground=CreatePlane()
	roof=CreatePlane()
	PositionEntity ground,0,(-width/2),0
	PositionEntity roof,0,(width/2),0
	RotateEntity roof,180,180,0

	groundtex=LoadTexture("textures/bark01.jpg")
	rooftex=LoadTexture("textures/pave03.jpg")
	ScaleTexture groundtex,10,10
	EntityTexture ground,groundtex
	EntityTexture roof,rooftex
	;ideEntity ground
	size=7
loadmap()

;For map.map=Each map
map.map=First map
RenderWorld
UpdateWorld
If map\envion<3
draw2d(map\area)
Flip
WaitKey
Cls
RenderWorld
UpdateWorld


; inside

draw3d(map\area)
;savemeshx(map\mesh,"MAP-"+map\name+".x")
If map\envion=0
;CameraFogColor camera,160,160,255
	CameraFogRange camera,5,45
;CameraClsColor camera,160,160,255
HideEntity roof
Else If map\envion=1 Or map\envion=2
;
	CameraFogRange camera,5,25
;CameraClsColor camera,20,20,20
ShowEntity roof
EndIf
If map\envion=3
;or a=0 To 255
;howEntity map\omesh[a]
;ext 
	HideEntity ground
	FlipMesh map\mesh
Else
;ShowEntity map\mesh
;	ShowEntity ground
End If
PositionEntity map\flor,0,EntityY#(map\flor)+.1,-2
If map\area=23
l=CopyEntity(map\mesh)
r=CopyEntity(map\mesh)
PositionEntity l,64,0,0
PositionEntity r,-64,0,0
EndIf

ShowEntity map\MESH


ShowEntity map\roof
ShowEntity map\coll
ShowEntity map\flor
EntityType map\coll,TYPE_WALL
EntityAlpha map\coll,.0
smap=False
While Not KeyHit(1)
RenderWorld
UpdateWorld
	X=Int(EntityX(camera)/width)
	z=Int(EntityZ(camera)/width+.5)
	;If z>15
	;ClearCollisions
	;PositionEntity camera,EntityX(camera),EntityY(camera),0
	;EndIf
	If x=1 Collisions TYPE_PLAYER,TYPE_WALL,2,3
	If x=14 Collisions TYPE_PLAYER,TYPE_WALL,2,3
	;If z=1 Collisions TYPE_PLAYER,TYPE_WALL,2,3
	;If z=14 Collisions TYPE_PLAYER,TYPE_WALL,2,3
	If x>15
	ClearCollisions
	PositionEntity camera,2,EntityY#(camera),EntityZ#(camera)
	EndIf
	If x<0
	ClearCollisions
	PositionEntity camera,62,EntityY#(camera),EntityZ#(camera)
	EndIf


	If KeyDown( 205 ) TurnEntity camera,0,-1,0
	If KeyDown( 203 ) TurnEntity camera,0,1,0
	If KeyDown( 208 ) MoveEntity camera,0,0,-0.05*(width/2) 
	If KeyDown( 200 ) MoveEntity camera,0,0, 0.05*(width/2) 
	If KeyDown(2) EntityAlpha map\coll,.1
	If KeyDown(3) EntityAlpha map\coll,0
		If KeyDown(46) ClearCollisions : collission=False
	If KeyDown(47) Collisions TYPE_PLAYER,TYPE_WALL,2,3 : collission=True
	If KeyDown(48) setbattlecamera()

	If KeyHit(50) smap=Not smap
	If KeyDown(57)  End

	
	X=Int(EntityX(camera)/width)
	z=Int(EntityZ(camera)/width+.5)
;	If z>15
;	PositionEntity camera,EntityX(camera),EntityY(camera),0
;	EndIf
	
	;Text 0,0,"Area:"+map\name+" X:"+Int(EntityX(camera)/width)+" Z:"+Int(EntityZ(camera)/width+.5)
	;Text 0,16,"FPS:"+fps() + " "+ TrisRendered() +" Mode:"+mode
	draw(mode,4) 
	If KeyHit(12) mode=mode-1
If KeyHit(13) mode=mode+1
	If KeyHit(3) size=2
		If KeyHit(4) size=3
			If KeyHit(5) size=4
				If KeyHit(6) size=5
					If KeyHit(7) size=6
						If KeyHit(8) size=7
If mode>9 mode=1 :Cls
If mode<1 mode=9 :Cls
	If mode=0
	CameraViewport camera,0,0,800,600
	Else
		CameraViewport camera,0,0,80,60
			EndIf
	If smap=1
	draw2da(map\area,x,z)
;	Flip
;	draw(mode)
;Flip



	done=0
	Repeat
	If KeyHit(50) done=1
	Until done=1
	smap=0
	EndIf
Flip
Cls
Wend
If map\area=23
	FreeEntity l
	FreeEntity r
EndIf
FreeEntity map\mesh

FreeEntity map\roof
FreeEntity map\coll
FreeEntity map\flor
FlushKeys()
EndIf
;Next
End

Function loadmap()
mapd=OpenFile("map.dat")
attrib=OpenFile("attrib.dat")

For area=0 To 59
map.map=New map
map\area=area
Select area
Case 0
	map\name$="Middlegate"
Case 1
	map\name$="Atlantium"
Case 2
	map\name$="Tundra"
Case 3
	map\name$="Volcania"
Case 4
	map\name$="Sandsobar"
Case 17
	map\name$="Middlegate Cavern"
Case 18
	map\name$="Atlantium Cavern"
Case 19
	map\name$="Tundra Cavern"
Case 20
	map\name$="Volcania Cavern"
Case 21
	map\name$="Sandsobar Cavern"
Case 22
	map\name$="Coraks Cave"
Case 23
	map\name$="Square Isle"
Case 24
	map\name$="Tundra Cave"
Case 25
	map\name$="Sarkins Mine"
Case 26
	map\name$="Murry's Resort"
Case 27
	map\name$="Druids Cavern"
Case 28
	map\name$="Forbiddin Forest Cavern"
Case 29
	map\name$="Dragons Dominion"
Case 30
	map\name$="Dawns Mist Bog"
Case 31
	map\name$="Gemmakers"	
Case 32
	map\name$="Normadic Rift"	
	Case 41
	map\name$="Elemental Plain of Air"	
Case 42
	map\name$="Elemental Plain of Fire"	
Case 43
	map\name$="Elemental Plain of Earth"	
Case 44
	map\name$="Elemental Plain of Water"	
Case 45
	map\name$="dungeon Level 1"	
Case 46
	map\name$="dungeon Level 2"	
Case 47
	map\name$="dungeon Level 1"	
Case 48
	map\name$="dungeon Level 2"	
Case 49
	map\name$="dungeon Level 1"	
Case 50
	map\name$="dungeon Level 2"	
Case 51
	map\name$="Woodhaven Dungeon level 1"	
Case 52
	map\name$="Woodhaven Dungeon Level 2"	

	Case 53
	map\name$="Ancients (Good)"	
	Case 54
	map\name$="Ancients (Evil)"	
	Case 55
	map\name$="Woodhaven"
		Case 56
	map\name$="Hillstone"	
	Case 57
	map\name$="Pinehurst"	
	Case 58
	map\name$="Luxus Palace Royale"	
	Case 59
	map\name$="the One in 800"	

End Select

aloc=(area*64)
mloc=(area*512)
SeekFile( mapd,mloc)
SeekFile(attrib,aloc+4)
tmp=ReadByte(attrib)
If tmp>0
	map\envion=3
	SeekFile(attrib,aloc+21)
	map\name$="Outside Area: "+Right(Hex(ReadByte(attrib)),2)
Else
	SeekFile(attrib,aloc+3)
	tmp=ReadByte(attrib)
	Select tmp
	Case 17
		map\envion=0
	Case 18
		map\envion=1
	Case 19
		map\envion=2
	Case 20
		map\envion=2
	End Select
	If map\area>40 And map\area<45
		map\envion=3
	EndIf
EndIf

;debuglog map\area+" :"+(FilePos(mapd))+" "+(FilePos(attrib))+" Envion:"+map\envion+" Name:"+map\name
SeekFile( mapd,mloc)
For loc=0 To 255
temp$=ReadByte(mapd)
ts$=Right$(Bin$(temp$),8)
map\mapdata[loc]=ts$
Next
SeekFile( mapd,mloc+256)
For loc=0 To 255
temp$=ReadByte(mapd)
ts$=Right$(Bin$(temp$),8)
map\collmap[loc]=ts$
Next

SeekFile(attrib,(area*64)+32)
For a=0 To 255 Step 8
roof=ReadByte(attrib)
;;debuglog "roof: "+a

;;debuglog Right(Bin(roof),8)
temparray(a+7)=Mid(Right(Bin(roof),8),1,1)
temparray(a+6)=Mid(Right(Bin(roof),8),2,1)
temparray(a+5)=Mid(Right(Bin(roof),8),3,1)
temparray(a+4)=Mid(Right(Bin(roof),8),4,1)
temparray(a+3)=Mid(Right(Bin(roof),8),5,1)
temparray(a+2)=Mid(Right(Bin(roof),8),6,1)
temparray(a+1)=Mid(Right(Bin(roof),8),7,1)
temparray(a+0)=Mid(Right(Bin(roof),8),8,1)

;map\roofdata2[a+7]=Mid(Right(Bin(roof),8),1,1)
;map\roofdata2[a+6]=Mid(Right(Bin(roof),8),2,1)
;map\roofdata2[a+5]=Mid(Right(Bin(roof),8),3,1)
;map\roofdata2[a+4]=Mid(Right(Bin(roof),8),4,1)
;map\roofdata2[a+3]=Mid(Right(Bin(roof),8),5,1)
;map\roofdata2[a+2]=Mid(Right(Bin(roof),8),6,1)
;map\roofdata2[a+1]=Mid(Right(Bin(roof),8),7,1)
;map\roofdata2[a+0]=Mid(Right(Bin(roof),8),8,1)
Next
For a=0 To 255
map\roofdata[a]=temparray(255-a)
Next

Next

				;Mid$(map\mapdata[a],5,4)=Mid$(ts$,1,4)
				;mapdata2$(x,y,loop,me,1)=Mid$(ts$,5,4)
				;mapdata$(x,y,0,loop,me)=Mid$(ts$,1,2)
				;mapdata$(x,y,1,loop,me)=Mid$(ts$,3,2)
				;mapdata$(x,y,2,loop,me)=Mid$(ts$,5,2)
				;mapdata$(x,y,3,loop,me)=Mid$(ts$,7,2)
tiles=LoadImage("Tiles.jpg")	
For map.map=Each map
UpdateWorld
RenderWorld
a=0

If map\envion=3

;For b=0 To 255
;map\omesh[b]=CreateMesh()
;Next
EndIf
Next
End Function
Function createwalla(pict,c,mesh,px,py)

surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;debuglog vert
For k=vert To segs+vert
;For k=0 To segs
	x#=Float(k-vert)*width/segs-width/2
	u#=(Float(k-vert)/segs)/3
	If pict=1 Or pict=4 Or pict=7 U#=u#
	If pict=2 Or pict=5 Or pict=8 U#=u#+.33333333
	If pict=3 Or pict=6 Or pict=9 U#=u#+.66666666


	AddVertex(surf,px+x,(width/2),py,u,0)
	AddVertex(surf,px+x,-(width/2),py,u,1)
	
Next
For k=vert To  (segs-1)+vert
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;If pict=1 paintwall(surf,pict)

End Function

Function createwallb(pict,c,mesh,px,py)
If c=1
EntityType mesh,type_wall
EntityAlpha mesh,0
EndIf
;surf=CreateSurface( mesh )
surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert

	x#=Float(k-vert)*width/segs-width/2
;	u#=Float(k-vert)/segs
U#=(Float(k-vert)/segs)/3
		If pict=1 Or pict=4 Or pict=7 U#=u#
	If pict=2 Or pict=5 Or pict=8 U#=u#+.33333333
	If pict=3 Or pict=6 Or pict=9 U#=u#+.66666666



;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px+(width/2),(width/2),(py-x)-(width/2),u,0
	AddVertex surf,px+(width/2),-(width/2),(py-X)-(width/2),u,1
	
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;If pict>=1 paintwall(surf,pict)
End Function

Function createwallc(pict,c,mesh,px,py)
If c=1
EntityType mesh,type_wall
EntityAlpha mesh,0
EndIf
;surf=CreateSurface( mesh )
surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	u#=Float(k-vert)/segs
	u#=(Float(k-vert)/segs)/3
	If pict=1 Or pict=4 Or pict=7 U#=u#
	If pict=2 Or pict=5 Or pict=8 U#=u#+.33333333
	If pict=3 Or pict=6 Or pict=9 U#=u#+.66666666

;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px-x,(width/2),py-(width),u,0
	AddVertex surf,px-x,-(width/2),py-(width),u,1
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)

	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
If pict>=1 paintwall(surf,pict)

End Function
Function createwalld(pict,c,mesh,px,py)
If c=1
EntityType mesh,type_wall
EntityAlpha mesh,0
EndIf
surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;surf=CreateSurface( mesh )
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	;u#=Float(k-vert)/segs
		u#=(Float(k-vert)/segs)/3
	If pict=1 Or pict=4 Or pict=7 U#=u#
	If pict=2 Or pict=5 Or pict=8 U#=u#+.33333333
	If pict=3 Or pict=6 Or pict=9 U#=u#+.66666666


;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px-(width/2),(width/2),(py+x)-(width/2),u,0
	AddVertex surf,px-(width/2),-(width/2),(py+x)-(width/2),u,1
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)

	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;If pict>=1 paintwall(surf,pict)
End Function
Function paintwall(surf,pict)
;		If pict=1
;			b=LoadBrush( "Textures\wall.jpg",8)
;
;		ElseIf pict=2
;			b=LoadBrush( "Textures\wall2.jpg",8)
;
;
;		ElseIf pict=3
;			b=LoadBrush( "Textures\wall3.jpg",8)
;		Else If pict=4
;			b=LoadBrush( "Textures\dwall.jpg",8)
;		ElseIf pict=5
;			b=LoadBrush( "Textures\dwall2.jpg",8)	
;		ElseIf pict=6
;			b=LoadBrush( "Textures\dwall3.jpg",8)
;		Else If pict=7
;			b=LoadBrush( "Textures\cwall.jpg",8)
;		ElseIf pict=8
;			b=LoadBrush( "Textures\cwall2.jpg",8)
;		ElseIf pict=9
;			b=LoadBrush( "Textures\cwall3.jpg",8)
;		EndIf
	b=LoadBrush( "Textures\twalls.jpg",8)

			PaintSurface surf,b

Return surf
End Function

Function createroof(mesh,px,py)
;mesh=CreateMesh()
;surf=CreateSurface( mesh )
surf=GetSurface(mesh,1)
vert=CountVertices(surf)

For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	u#=Float(k-vert)/segs
	AddVertex surf,px+x,py+(width/2),1,u,0
	AddVertex surf,px+x,py-(width/2),1,u,1
Next
For k=vert To vert+(segs-1)
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
	
	b=LoadBrush( "Textures\road.jpg")
	PaintSurface surf,b

;RotateMesh mesh,270,-90,0
;Return mesh
End Function

Function setbattlecamera()
;	ocx=EntityX#(camera)
;	ocy=EntityY#(camera)
;	ocz=EntityZ#(camera)
;	ocr=EntityRoll#(camera)
;	ocp=EntityPitch#(camera)
;	oca=EntityYaw#(camera)
;	UpdateWorld
;	RenderWorld
;	For a=0 To 6
;	UpdateWorld
;	RenderWorld
;	RotateEntity camera,7.5*a,40,0
;	b#=b#+.1
;	;debuglog b
;	MoveEntity camera,b*PIXSZ,b*2,b*4
;	Flip
;	Next
End Function



Function createfloor(mesh,px,py,pict)
;mesh=CreateMesh()
;surf=CreateSurface( mesh )
surf=GetSurface(mesh,1)
vert=CountVertices(surf)
For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	u#=(Float(k-vert)/segs)/6
	Select pict
	Case 1
	v2#=0
	Case 2
	v2#=.16666667
	Case 3
	v2#=.33333333
	Case 4
	v2#=.5
	Case 5
	v2#=.66666666
	Case 6
	v2#=.83333333
	End Select
	u#=u#+v2#
	;debuglog v#
	AddVertex surf,px+x,py+(width/2),1,u,0
	AddVertex surf,px+x,py-(width/2),1,u,1
Next
For k=vert To vert+(segs-1)
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;	
;	If pict=1
b=LoadBrush( "Textures\groundoss.jpg")
PaintSurface surf,b
;ElseIf pict=2
;	b=LoadBrush( "Textures\water.jpg")
;PaintSurface surf,b
;ElseIf pict=3
;	b=LoadBrush( "Textures\snow.jpg")
;PaintSurface surf,b
;ElseIf pict=4
;	b=LoadBrush( "Textures\swamp.jpg")
;PaintSurface surf,b
;ElseIf pict=5
;	b=LoadBrush( "Textures\desert.jpg")
;PaintSurface surf,b
;ElseIf pict=6
;	b=LoadBrush( "Textures\road.jpg")
;PaintSurface surf,b
;EndIf
End Function



Function Draw2d(mapno)
tiles=LoadImage("Tiles.jpg")
For map.map=Each map
If map\area=mapno

If map\envion<3

	For x=0 To 15
		For y=0 To 15
		ya=160-(x*10)
		xa=10+(y*10)
	If Mid$(map\collmap[a],1,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],1,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],1,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],1,2)="11"
			Color(125,125,255)
			EndIf
			Line 200+xa,ya,200+xa+9,ya
			If Mid$(map\collmap[a],3,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],3,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],3,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],3,2)="11"
			Color(125,125,255)
			EndIf
			Line 200+xa+9,ya+9,200+xa+9,ya
			If Mid$(map\collmap[a],5,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],5,2)="01"
			Color(125,125,125)
				ElseIf Mid$(map\collmap[a],5,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],5,2)="11"
			Color(125,125,255)
			EndIf
			Line 200+xa,ya+9,200+xa+9,ya+9
		
			If Mid$(map\collmap[a],7,2)="00"
				Color(0,0,0)
			ElseIf Mid$(map\collmap[a],7,2)="01"
				Color(125,125,125)
			ElseIf Mid$(map\collmap[a],7,2)="10"
				Color(255,255,255)
			ElseIf Mid$(map\collmap[a],7,2)="11"
				Color(125,125,255)
			EndIf
		    Line 200+xa,ya,200+xa,ya+9


			If Mid$(map\mapdata[a],1,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],1,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],1,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],1,2)="11"
			Color(125,125,255)
			EndIf
			Line xa,ya,xa+9,ya
			If Mid$(map\mapdata[a],3,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],3,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],3,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],3,2)="11"
			Color(125,125,255)
			EndIf
			Line xa+9,ya+9,xa+9,ya
			If Mid$(map\mapdata[a],5,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],5,2)="01"
			Color(125,125,125)
				ElseIf Mid$(map\mapdata[a],5,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],5,2)="11"
			Color(125,125,255)
			EndIf
			Line xa,ya+9,xa+9,ya+9
		
			If Mid$(map\mapdata[a],7,2)="00"

			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],7,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],7,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],7,2)="11"
			Color(125,125,255)
			EndIf
		    Line xa,ya,xa,ya+9
			If map\roofdata[a]=0 Then Color 0,0,0
			If map\roofdata[a]=1 Then Color 255,255,0
			Rect 15-x*PIXSZ+140,y*PIXSZ+220,7,7,1
			a=a+1

Next
Next
Color(255,255,255)
Text 0,0,map\area+" :"+" Envion:"+map\envion+" Name:"+map\name

Else If map\envion=3
	Text 0,0,map\area+" :"+" Envion:"+map\envion+" Name:"+map\name
	
	For x=0 To 15
		For y=0 To 15
		xa=16-(x)

	If Mid$(map\mapdata[a],1,4)="0000"
			oy=15
		ElseIf Mid$(map\mapdata[a],1,4)="0001"
			oy=14
		ElseIf Mid$(map\mapdata[a],1,4)="0010"
			oy=13
		ElseIf Mid$(map\mapdata[a],1,4)="0011"
			oy=12
		ElseIf Mid$(map\mapdata[a],1,4)="0100"
			oy=11
		ElseIf Mid$(map\mapdata[a],1,4)="0101"
			oy=10
		ElseIf Mid$(map\mapdata[a],1,4)="0110"
			oy=9
		ElseIf Mid$(map\mapdata[a],1,4)="0111"
			oy=8
		ElseIf Mid$(map\mapdata[a],1,4)="1000"
			oy=7
		ElseIf Mid$(map\mapdata[a],1,4)="1001"
			oy=6
		ElseIf Mid$(map\mapdata[a],1,4)="1010"
			oy=5
		ElseIf Mid$(map\mapdata[a],1,4)="1011"
			oy=4
		ElseIf Mid$(map\mapdata[a],1,4)="1100"
			oy=3
		ElseIf Mid$(map\mapdata[a],1,4)="1101"
			oy=2
		ElseIf Mid$(map\mapdata[a],1,4)="1110"
			oy=1
		ElseIf Mid$(map\mapdata[a],1,4)="1111"
			oy=0
		EndIf
		If Mid$(map\mapdata[a],5,4)="0000"
			ox=0

		ElseIf Mid$(map\mapdata[a],5,4)="0001"
			ox=1
		ElseIf Mid$(map\mapdata[a],5,4)="0010"
			ox=2
		ElseIf Mid$(map\mapdata[a],5,4)="0011"
			ox=3
		ElseIf Mid$(map\mapdata[a],5,4)="0100"
			ox=4
		ElseIf Mid$(map\mapdata[a],5,4)="0101"
			ox=5
		ElseIf Mid$(map\mapdata[a],5,4)="0110"
			ox=6
		ElseIf Mid$(map\mapdata[a],5,4)="0111"
			ox=7
		ElseIf Mid$(map\mapdata[a],5,4)="1000"
			ox=8
		ElseIf Mid$(map\mapdata[a],5,4)="1001"
			ox=9
		ElseIf Mid$(map\mapdata[a],5,4)="1010"
			ox=10
		ElseIf Mid$(map\mapdata[a],5,4)="1011"
			ox=11
		ElseIf Mid$(map\mapdata[a],5,4)="1100"
			ox=12
		ElseIf Mid$(map\mapdata[a],5,4)="1101"
			ox=13
		ElseIf Mid$(map\mapdata[a],5,4)="1110"
			ox=14
		ElseIf Mid$(map\mapdata[a],5,4)="1111"
			ox=15
		EndIf

	
		;createfloor(map\roof,x*width,y*width,6)
		DrawBlockRect tiles,(y)*28,(xa)*22,((ox)*28),(oy)*22,28,22
		a=a+1
		
Next
Next
EndIf
End If
Next

End Function



Function draw3d(map_no)

For map.map=Each map
If map\area=map_no
map\roof=CreateMesh()
map\coll=CreateMesh()
map\mesh=CreateMesh()
map\flor=CreateMesh()

map\meshsurf=CreateSurface(map\MESH)
map\roofsurf=CreateSurface(map\roof)
map\collsurf=CreateSurface(map\coll)
map\collsurf=CreateSurface(map\flor)

If map\envion<3

	For x=0 To 15
		For y=0 To 15
		If map\envion=0
			If map\roofdata[a]=1 
				createroof(map\roof,x*width,y*width)
			EndIf
		EndIf
		ya=160-(x*10)
		xa=10+(y*10)
		If map\envion=0
			p1=1
			p2=2
			p3=3
		Else If map\envion=1
			p1=4
			p2=5
			p3=6
		Else If map\envion=2
			p1=7
			p2=8
			p3=9
		EndIf
		If Mid$(map\collmap[a],1,2)="00"

			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],1,2)="01"
;			;debuglog "map: 01"
			createwalla(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],1,2)="10"
;						;debuglog "map: 10"
			createfloor(map\flor,x*width#,y*width#,0)

;			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],1,2)="11"
;						;debuglog "map: 11"
			createfloor(map\flor,x*width#,y*width#,0)
createwalla(0,0,map\coll,y*width#,x*width#)


;			Color(125,125,255)
			EndIf
			If Mid$(map\collmap[a],3,2)="00"
;			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],3,2)="01"
			createwallb(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],3,2)="10"
;			Color(255,255,255)
	createfloor(map\flor,x*width#,y*width#,0)

			ElseIf Mid$(map\collmap[a],3,2)="11"
			createwallb(0,0,map\coll,y*width#,x*width#)
	createfloor(map\flor,x*width#,y*width#,0)

			Color(125,125,255)
			EndIf
			If Mid$(map\collmap[a],5,2)="00"
;			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],5,2)="01"
						createwallc(0,0,map\coll,y*width#,x*width#)

;			Color(125,125,125)
				ElseIf Mid$(map\collmap[a],5,2)="10"
											createfloor(map\flor,x*Width,y*width,0)

			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],5,2)="11"
							createfloor(map\flor,x*width,y*width,0)

							createwallc(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,255)
			EndIf
		
			If Mid$(map\collmap[a],7,2)="00"
				Color(0,0,0)
			ElseIf Mid$(map\collmap[a],7,2)="01"
				createwalld(0,0,map\coll,y*width#,x*width#)

;				Color(125,125,125)
			ElseIf Mid$(map\collmap[a],7,2)="10"
							createfloor(map\flor,x*width,y*width,0)

;				Color(255,255,255)
			ElseIf Mid$(map\collmap[a],7,2)="11"
				createwalld(0,0,map\coll,y*width#,x*width#)
							createfloor(map\flor,x*width,y*width,0)

;				Color(125,125,255)
			EndIf



			If Mid$(map\mapdata[a],1,2)="00"
			ElseIf Mid$(map\mapdata[a],1,2)="01"
						;debuglog "map: 01"
			createwalla(p1,0,map\MESH,y*width#,x*width#) ; normal wall
			ElseIf Mid$(map\mapdata[a],1,2)="10"
						;debuglog "map: 10"
			createwalla(p2,0,map\mesh,y*width#,x*width#) ; Door wall
			ElseIf Mid$(map\mapdata[a],1,2)="11"
						;debuglog "map: 11"
			createwalla(p3,0,map\mesh,y*width#,x*width#) ; Torch on wall
			EndIf
			If Mid$(map\mapdata[a],3,2)="00"
			ElseIf Mid$(map\mapdata[a],3,2)="01"
			createwallb(p1,0,map\MESH,y*width#,x*width#)
			ElseIf Mid$(map\mapdata[a],3,2)="10"
				createwallb(p2,0,map\mesh,y*width#,x*width#)
			ElseIf Mid$(map\mapdata[a],3,2)="11"
			createwallb(p3,0,map\mesh,y*width#,x*width#)
			EndIf
			If Mid$(map\mapdata[a],5,2)="00"
			ElseIf Mid$(map\mapdata[a],5,2)="01"
						createwallc(p1,0,map\MESH,y*width#,x*width#)
				ElseIf Mid$(map\mapdata[a],5,2)="10"
							createwallc(p2,0,map\mesh,y*width#,x*width#)
			ElseIf Mid$(map\mapdata[a],5,2)="11"
							createwallc(p3,0,map\mesh,y*width#,x*width#)
			EndIf
		
			If Mid$(map\mapdata[a],7,2)="00"

			ElseIf Mid$(map\mapdata[a],7,2)="01"
						createwalld(p1,0,map\MESH,y*width#,x*width#)
			ElseIf Mid$(map\mapdata[a],7,2)="10"
							createwalld(p2,0,map\mesh,y*width#,x*width#)
			ElseIf Mid$(map\mapdata[a],7,2)="11"
							createwalld(p3,0,map\mesh,y*width#,x*width#)
			EndIf


			a=a+1

Next
Next
Color(255,255,255)
;Text 0,0,map\area+" :"+(FilePos(mapd))+" "+(FilePos(attrib))+" Envion:"+map\envion+" Name:"+map\name
RotateMesh map\roof,270,-90,0
MoveEntity map\roof,30*(width/2),0+((width/2)-1),29*(width/2)
Else If map\envion=3
;Text 0,0,map\area+" :"+(FilePos(mapd))+" "+(FilePos(attrib))+" Envion:"+map\envion+" Name:"+map\name
	
	For x=0 To 15
		For y=0 To 15
	xa=16-(x)


If Mid$(map\collmap[a],1,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],1,2)="01"
;			;debuglog "map: 01"
			createwalla(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],1,2)="10"
;						;debuglog "map: 10"
createfloor(map\flor,x*width,y*width,1)

;			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],1,2)="11"
;						;debuglog "map: 11"
			createwalla(0,0,map\coll,y*width#,x*width#)
createfloor(map\flor,x*width,y*width,1)

;			Color(125,125,255)
			EndIf
			If Mid$(map\collmap[a],3,2)="00"
;			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],3,2)="01"
			createwallb(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,125)
			ElseIf Mid$(map\collmap[a],3,2)="10"
;			Color(255,255,255)
createfloor(map\flor,x*width,y*width,1)

			ElseIf Mid$(map\collmap[a],3,2)="11"
			createwallb(0,0,map\coll,y*width#,x*width#)
			createfloor(map\flor,x*width,y*width,1)

			Color(125,125,255)
			EndIf
			If Mid$(map\collmap[a],5,2)="00"
;			Color(0,0,0)
			ElseIf Mid$(map\collmap[a],5,2)="01"
						createwallc(0,0,map\coll,y*width#,x*width#)
						createfloor(map\flor,x*width,y*width,1)

;			Color(125,125,125)
				ElseIf Mid$(map\collmap[a],5,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\collmap[a],5,2)="11"
			createfloor(map\flor,x*width,y*width,1)

							createwallc(0,0,map\coll,y*width#,x*width#)
;			Color(125,125,255)
			EndIf
		
			If Mid$(map\collmap[a],7,2)="00"
				Color(0,0,0)
			ElseIf Mid$(map\collmap[a],7,2)="01"
				createwalld(0,0,map\coll,y*width#,x*width#)

;				Color(125,125,125)
			ElseIf Mid$(map\collmap[a],7,2)="10"
							createfloor(map\flor,x*width,y*width,1)

;				Color(255,255,255)
			ElseIf Mid$(map\collmap[a],7,2)="11"
				createwalld(0,0,map\coll,y*width#,x*width#)
				createfloor(map\flor,x*width,y*width,1)

;				Color(125,125,255)
			EndIf
;		;debuglog Mid$(map\mapdata[a],5,4)
If Mid$(map\mapdata[a],1,4)="0000"
			oy=15
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="0001"
			oy=14
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="0010"
			oy=13
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="0011"
			oy=12
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="0100"
			oy=11
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="0101"
			oy=10
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="0110"
			oy=9
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="0111"
			oy=8
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="1000"
			oy=7
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="1001"
			oy=6
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="1010"
			oy=5
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="1011"
			oy=4
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="1100"
			oy=3
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="1101"
			oy=2
			T=1
		ElseIf Mid$(map\mapdata[a],1,4)="1110"
			oy=1
			T=0
		ElseIf Mid$(map\mapdata[a],1,4)="1111"
			oy=0
			T=1
		EndIf
If t=0		
		If Mid$(map\mapdata[a],5,4)="0000"
			ox=0
	createfloor(map\flor,x*width,y*width,1)

		ElseIf Mid$(map\mapdata[a],5,4)="0001"
			ox=1
			createowall(3,y*width#,x*width#,map\mesh)
	createfloor(map\flor,x*width,y*width,1)
		ElseIf Mid$(map\mapdata[a],5,4)="0010"
			ox=2
			createowall(3,y*width#,x*width#,map\mesh)
	createfloor(map\flor,x*width,y*width,1)
		ElseIf Mid$(map\mapdata[a],5,4)="0011"
			ox=3
			createowall(1,y*width#,x*width#,map\mesh)
	createfloor(map\flor,x*width,y*width,1)
		ElseIf Mid$(map\mapdata[a],5,4)="0100"
			ox=4
			createowall(2,y*width#,x*width#,map\mesh)
				createfloor(map\flor,x*width,y*width,1)
		ElseIf Mid$(map\mapdata[a],5,4)="0101"
			ox=5
				createfloor(map\flor,x*width,y*width,5)
		ElseIf Mid$(map\mapdata[a],5,4)="0110"
			ox=6
				createfloor(map\flor,x*width,y*width,2)
		ElseIf Mid$(map\mapdata[a],5,4)="0111"
			ox=7
				createfloor(map\flor,x*width,y*width,4)
		ElseIf Mid$(map\mapdata[a],5,4)="1000"
			ox=8
				createfloor(map\flor,x*width,y*width,3)
		ElseIf Mid$(map\mapdata[a],5,4)="1001"
			ox=9
				createfloor(map\flor,x*width,y*width,5)
		ElseIf Mid$(map\mapdata[a],5,4)="1010"
			ox=10
				createfloor(map\flor,x*width,y*width,2)
		ElseIf Mid$(map\mapdata[a],5,4)="1011"
			ox=11
				createfloor(map\flor,x*width,y*width,4)
		ElseIf Mid$(map\mapdata[a],5,4)="1100"
			ox=12
				createfloor(map\flor,x*width,y*width,3)
		ElseIf Mid$(map\mapdata[a],5,4)="1101"
			ox=13
				createfloor(map\flor,x*width,y*width,6)
		ElseIf Mid$(map\mapdata[a],5,4)="1110"
			ox=14
				createfloor(map\flor,x*width,y*width,1)
			createowall(3,y*width#,x*width#,map\mesh)

		ElseIf Mid$(map\mapdata[a],5,4)="1111"
			ox=15
				;createfloor(map\roof,x*width,y*width,1)
		EndIf

	Else 	
	createfloor(map\flor,x*width,y*width,6)

		If Mid$(map\mapdata[a],5,4)="0000"
			ox=0
	
		ElseIf Mid$(map\mapdata[a],5,4)="0001"
			ox=1
		ElseIf Mid$(map\mapdata[a],5,4)="0010"
			ox=2
		ElseIf Mid$(map\mapdata[a],5,4)="0011"
			ox=3
		ElseIf Mid$(map\mapdata[a],5,4)="0100"
			ox=4
		ElseIf Mid$(map\mapdata[a],5,4)="0101"
			ox=5
		ElseIf Mid$(map\mapdata[a],5,4)="0110"
			ox=6
		ElseIf Mid$(map\mapdata[a],5,4)="0111"
			ox=7
		ElseIf Mid$(map\mapdata[a],5,4)="1000"
			ox=8
		ElseIf Mid$(map\mapdata[a],5,4)="1001"
			ox=9
		ElseIf Mid$(map\mapdata[a],5,4)="1010"
			ox=10
		ElseIf Mid$(map\mapdata[a],5,4)="1011"
			ox=11
		ElseIf Mid$(map\mapdata[a],5,4)="1100"
			ox=12
		ElseIf Mid$(map\mapdata[a],5,4)="1101"
			ox=13
		ElseIf Mid$(map\mapdata[a],5,4)="1110"
			ox=14
		ElseIf Mid$(map\mapdata[a],5,4)="1111"
			ox=15
		EndIf
	EndIf
		;createfloor(map\roof,x*width,y*width,6)
;		DrawBlockRect tiles,(y)*28,(xa)*22,((ox)*28),(oy)*22,28,22
		;FlipMesh map\mesh
		;HideEntity map\mesh
		
		a=a+1
		
Next
Next

EndIf
HideEntity map\mesh

HideEntity map\roof
HideEntity map\flor

HideEntity map\coll
;WaitKey
RotateMesh map\flor,270,90,0
FlipMesh map\flor
;MoveEntity map\roof,0,0-((width/2)-1),-2
MoveEntity  map\flor,EntityX#(map\roof),0-((width)-1),-2
End If


Next
End Function

Function createowall(pict,px,py,mesh)
surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;debuglog vert
For k=vert To segs+vert
;For k=0 To segs
	x#=Float(k-vert)*width/segs-width/2
	u#=(Float(k-vert)/segs)/3
	If pict=1 U#=u#
	If pict=2 U#=u#+.33333333
	If pict=3 U#=u#+.66666666

	AddVertex(surf,px+x,(width/2),py,u,0)
	AddVertex(surf,px+x,-(width/2),py,u,1)
	
Next
For k=vert To  (segs-1)+vert
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;surf=CreateSurface( mesh )
vert=CountVertices(surf)
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert

	x#=Float(k-vert)*width/segs-width/2
;	u#=Float(k-vert)/segs
U#=(Float(k-vert)/segs)/3
	If pict=1 U#=u#
	If pict=2 U#=u#+.33333333
	If pict=3 U#=u#+.66666666
;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px+(width/2),(width/2),(py-x)-(width/2),u,0
	AddVertex surf,px+(width/2),-(width/2),(py-X)-(width/2),u,1
	
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)
	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next

;surf=CreateSurface( mesh )
;surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	u#=Float(k-vert)/segs
	u#=(Float(k-vert)/segs)/3
	If pict=1 U#=u#
	If pict=2 U#=u#+.33333333
	If pict=3 U#=u#+.66666666


;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px-x,(width/2),py-(width),u,0
	AddVertex surf,px-x,-(width/2),py-(width),u,1
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)

	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next

;surf=GetSurface(mesh,1)
vert=CountVertices(surf)
;surf=CreateSurface( mesh )
;;debuglog surf
;For k=0 To segs
For k=vert To segs+vert
	x#=Float(k-vert)*width/segs-width/2
	;u#=Float(k-vert)/segs
		u#=(Float(k-vert)/segs)/3
	If pict=1 U#=u#
	If pict=2 U#=u#+.33333333
	If pict=3 U#=u#+.66666666

;	;debuglog px
;	;debuglog py
;	;debuglog X#
;	;debuglog "__"
;	;debuglog px+x
;	;debuglog py+(-1)
	AddVertex surf,px-(width/2),(width/2),(py+x)-(width/2),u,0
	AddVertex surf,px-(width/2),-(width/2),(py+x)-(width/2),u,1
Next
;For k=0 To segs-1
For k=vert To vert+(segs-1)

	AddTriangle surf,k,k+2,k+3
	AddTriangle surf,k,k+3,k+1	
Next
;If pict>=1 paintwall(surf,pict)
b=LoadBrush( "Textures\otwalls.jpg",0)
			PaintSurface surf,b

End Function


Function FirstFps()
	count=0
	timer=MilliSecs()
End Function

Function Fps()
	oldtimer=timer
	timer=MilliSecs()

	count=count+1
	If count>60 Then count=60:i.fpsss=First fpsss:Delete i

	i.fpsss=New fpsss
	i\time=timer-oldtimer

	divide#=0
	totaltime#=0
	For i.fpsss=Each fpsss
		divide#=divide#+1.0
		totaltime#=totaltime#+i\time
	Next
	Return (1000.0*divide#)/(totaltime#)
End Function


Function Draw2da(mapno,px,pz)
;tiles=LoadImage("Tiles.jpg")
For map.map=Each map
If map\area=mapno

;If map\envion<3

	For x=0 To 15
		For y=0 To 15
		ya=200-(x*10)
		xa=120+(y*10)
			If Mid$(map\mapdata[a],1,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],1,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],1,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],1,2)="11"
			Color(125,125,255)
			EndIf
			Line xa,ya,xa+9,ya
			If Mid$(map\mapdata[a],3,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],3,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],3,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],3,2)="11"
			Color(125,125,255)
			EndIf
			Line xa+9,ya+9,xa+9,ya
			If Mid$(map\mapdata[a],5,2)="00"
			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],5,2)="01"
			Color(125,125,125)
				ElseIf Mid$(map\mapdata[a],5,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],5,2)="11"
			Color(125,125,255)
			EndIf
			Line xa,ya+9,xa+9,ya+9
		
			If Mid$(map\mapdata[a],7,2)="00"

			Color(0,0,0)
			ElseIf Mid$(map\mapdata[a],7,2)="01"
			Color(125,125,125)
			ElseIf Mid$(map\mapdata[a],7,2)="10"
			Color(255,255,255)
			ElseIf Mid$(map\mapdata[a],7,2)="11"
			Color(125,125,255)
			EndIf
		    Line xa,ya,xa,ya+9
			a=a+1
			Color(255,0,255)
			If x=pz And y=px	
			Rect xa,ya,8,8
			;debuglog px
			;debuglog pz
			;debuglog xa
			;debuglog ya
			;debuglog "++"
			EndIf
			

			
Next
Next
EndIf
;EndIf
Next
Color(255,255,255)


End Function

Function draw(mode,pixsz=2)
If mode<>0
LockBuffer BackBuffer()
	For a=0 To 80
		For b=0 To 60
			pcol(a,b)=ReadPixelFast(a,b)
		Next
	Next
	UnlockBuffer (BackBuffer)
	Color 0,0,0
	Rect 0,0,80,60
If mode<9 LockBuffer BackBuffer()
	For c=1 To 80
		For d=1 To 60
	;	If pred(c,d)=0
			If Not pcol(c,d)=-16777216 ;hen pcol(c,d)=$FFFFFF
				a=c
				b=d
;smile
If mode=1
WritePixelFast a*PIXSZ+1,b*PIXSZ,pcol(c,d)
WritePixelFast a*PIXSZ+4,b*PIXSZ,pcol(c,d)
WritePixelFast a*PIXSZ+1,b*PIXSZ+3,pcol(c,d)
WritePixelFast a*PIXSZ+4,b*PIXSZ+3,pcol(c,d)
WritePixelFast a*PIXSZ+2,b*PIXSZ+4,pcol(c,d)
WritePixelFast a*PIXSZ+3,b*PIXSZ+4,pcol(c,d)
Else If mode>1 And mode<4
WritePixelFast a*PIXSZ,b*PIXSZ,pcol(c,d)

WritePixelFast a*PIXSZ+1,b*PIXSZ+1,pcol(c,d)
WritePixelFast a*PIXSZ+1,b*PIXSZ-1,pcol(c,d)
WritePixelFast a*PIXSZ-1,b*PIXSZ+1,pcol(c,d)
WritePixelFast a*PIXSZ-1,b*PIXSZ-1,pcol(c,d)
	If mode=3
		WritePixelFast a*PIXSZ+2,b*PIXSZ+2,pcol(c,d)
		WritePixelFast a*PIXSZ+2,b*PIXSZ-2,pcol(c,d)
		WritePixelFast a*PIXSZ-2,b*PIXSZ+2,pcol(c,d)
		WritePixelFast a*PIXSZ-2,b*PIXSZ-2,pcol(c,d)

		WritePixelFast a*PIXSZ+1,b*PIXSZ,pcol(c,d)
		WritePixelFast a*PIXSZ,b*PIXSZ-1,pcol(c,d)
		WritePixelFast a*PIXSZ,b*PIXSZ+1,pcol(c,d)
		WritePixelFast a*PIXSZ-1,b*PIXSZ,pcol(c,d)
	EndIf
	Else If mode=4

	For z=1 To pixsz Step 3
	For x=1 To pixsz Step 3
	WritePixelFast a*PIXSZ+x,b*PIXSZ+z,pcol(c,d)
	Next
	Next
	Else If	mode=5

	For z=1 To pixsz Step 2
	For x=1 To pixsz Step 2
	WritePixelFast a*PIXSZ+lsin(Rnd(360)),b*PIXSZ+lsin(Rnd(360)),pcol(c,d)
	Next
	Next

	Else If mode=6

	For z=1	To pixsz Step 1
	For x=1 To pixsz Step 1
	WritePixelFast a*PIXSZ+x,b*PIXSZ+z,pcol(c,d)
	Next
	Next
	Else If mode=7
	;WritePixelFast a*PIXSZ,b*PIXSZ,pcol(c,d)
	;WritePixelFast a*PIXSZ+1,b*PIXSZ,pcol(c,d)
	For x=0 To 7
	WritePixelFast a*PIXSZ+x,b*PIXSZ+x,pcol(c,d)
	Next
		For x=0 To 7
	WritePixelFast a*PIXSZ+x,b*PIXSZ+x+2,pcol(c,d)
	Next

	Else If	mode=8
	;setrgb(pcol(c,d))
	;	Color Rnd(255),Rnd(255),Rnd(255)
	;	Text (a*PIXSZ),b*PIXSZ,"H"
	WritePixelFast a*PIXSZ,b*PIXSZ,pcol(c,d)
	For x=1 To 6
	WritePixelFast a*PIXSZ,b*PIXSZ+x,pcol(c,d)
	Next
	For x=1 To 6
	WritePixelFast a*PIXSZ+7,b*PIXSZ+x,pcol(c,d)
	Next
	For x=1 To 6
		WritePixelFast a*PIXSZ+x,b*PIXSZ+3,pcol(c,d)

	Next
	Else If	mode=9
	For x=0 To 7
	WritePixelFast a*PIXSZ-x,b*PIXSZ+x,pcol(c,d)
	Next
	Else If mode=10
	setrgb(pcol(c,d))
	Rect a*PIXSZ,b*PIXSZ,PIXSZ,PIXSZ

		EndIf
EndIf
;EndIf

Next
Next
If mode<9 UnlockBuffer (BackBuffer)
EndIf
End Function


Function getRGB(red%,Green%,Blue%)
Return (red Shl 16) + (Green Shl 8) + (blue)
End Function

Function setRGB(col)
colour$=Right(Bin(col),24)
red$=Left(Colour,8)
green$=Mid(Colour,9,8)
blue$=Right(colour,8)
r=Rnd(255);bintodec(red)
;g=bintodec(green)
;b=bintodec(blue)

Color r,g,b
End Function

Function bintodec(S$)
	For a=1 To 8
	temp=Mid(S,a,1)
	If temp=1
		Select a
		 Case 1
			dec=dec+128
		 Case 2
			dec=dec+64
		 Case 3
			dec=dec+32
		 Case 4
			dec=dec+16
		 Case 5
			dec=dec+8
		 Case 6
			dec=dec+4
		 Case 7
			dec=dec+2
		 Case 8
			dec=dec+1
		End Select
	EndIf
	Next
Return dec

End Function
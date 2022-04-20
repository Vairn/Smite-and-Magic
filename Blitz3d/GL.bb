Function  InitGL()										; All Setup For OpenGL Goes Here
	glEnable(GL_TEXTURE_2D);							; Enable Texture Mapping ( NEW )
	glShadeModel(GL_SMOOTH);							; Enable Smooth Shading
	;glClearColor(0.7, 0.8, 1.0, 1.0);				; Black Background
	glClearDepth(1.0)
	glEnable(GL_DEPTH_TEST);							; Enables Depth Testing
	glDepthFunc(GL_LEQUAL);								; The Type Of Depth Testing To Do
	glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);	; Really Nice Perspective Calculations
	BuildFont()
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
	loadtextures()

	glAlphaFunc(GL_EQUAL,1.0)
	glEnable(GL_ALPHA_TEST)	
End Function

Function loadTextures()
	texture[0]=bglLoadTexture("D:\Adams\Blitz\BlitzGL\MM2\Textures\twall.bmp",0)
	texture[1]=bglLoadTexture("textures\twall.bmp",0)
	texture[2]=bglLoadTexture("textures\twall.bmp",0)
;	texture[1]=bglLoadTexture("textures\twall2.jpg",1)
;	texture[2]=bglLoadTexture("textures\twall3.jpg",1)
	texture[3]=bglLoadTexture("textures\dwallb.bmp",1)
	texture[4]=bglLoadTexture("textures\dwall2b.bmp",1)
	texture[5]=bglLoadTexture("textures\dwall3b.bmp",1)
	texture[6]=bglLoadTexture("textures\cwall.jpg",1)
	texture[7]=bglLoadTexture("textures\cwall2.jpg",1)
	texture[8]=bglLoadTexture("textures\cwall3.jpg",1)
	texture[9]=bglLoadTexture("textures\owall.bmp",0)
	texture[10]=bglLoadTexture("textures\owall2.jpg",0)
	texture[11]=bglLoadTexture("textures\owall3.bmp",0)		
	texture[12]=bglLoadTexture("textures\road.jpg",1)
	texture[13]=bglLoadTexture("textures\pave03.jpg",1)	
	texture[14]=bglLoadTexture("Textures\grass.jpg",1)
	texture[15]=bglLoadTexture("Textures\water.jpg",1)
	texture[16]=bglLoadTexture("Textures\snow.jpg",1)
	texture[17]=bglLoadTexture("Textures\swamp.jpg",1)
	texture[18]=bglLoadTexture("Textures\sand.jpg",1)
	texture[19]=bglLoadTexture("Textures\road.jpg",1)
	font=bglLoadTexture("data\font32.bmp",0)
	For a=0 To 29
		texminimap[a]=bglLoadTexture("tiles\image"+(a+1)+".bmp",1)
	Next
End Function

Function ReSizeGLScene(width#,height#)		; Resize And Initialize The GL Window

	If (height=0)										; Prevent A Divide By Zero By
	
		height=1;										; Making Height Equal One
	EndIf

	glViewport(0,100,width-130,height-100);						; Reset The Current Viewport

	glMatrixMode(GL_PROJECTION);						; Select The Projection Matrix
	glLoadIdentity();									; Reset The Projection Matrix

	; Calculate The Aspect Ratio Of The Window
	gluPerspective(45.0,width/height,0.01,100.0);

	glMatrixMode(GL_MODELVIEW);							; Select The Modelview Matrix
	glLoadIdentity();									; Reset The Modelview Matrix
End Function
Function SizeGLScene(x#,y#,width#,height#)		; Resize And Initialize The GL Window

	If (height=0)										; Prevent A Divide By Zero By
	
		height=1;										; Making Height Equal One
	EndIf

	glViewport(x,y,width,height);						; Reset The Current Viewport
	wh#=width-x
	ht#=height-y
	glMatrixMode(GL_PROJECTION);						; Select The Projection Matrix
	glLoadIdentity();									; Reset The Projection Matrix

	; Calculate The Aspect Ratio Of The Window
	gluPerspective(45.0,wh/ht,0.1,100.0);

	glMatrixMode(GL_MODELVIEW);							; Select The Modelview Matrix
	glLoadIdentity();									; Reset The Modelview Matrix
End Function


Function DrawGLScene()
	
	glClear(GL_COLOR_BUFFER_BIT Or GL_DEPTH_BUFFER_BIT Or GL_STENCILBUFFER_BIT);
	glLoadIdentity();									; Reset Modelview Matrix
	;statusbar()
	scx#=Float(winwid)/640.
	scy#=Float(winhgt)/480.
;	DebugLog SCX+" "+SCY+" !"
	SizeGLScene((15*scx),(scy*145),winwid-(170*scx),winhgt-(185*scy))
	gluLookAt(camera\PX,camera\PY,camera\PZ,camera\VX,camera\VY,camera\VZ,camera\uX,camera\uY,camera\uZ)
	Select direction
	Case 0	; n
		glTranslatef(0,0,-.15)
	Case 1	; e
		glTranslatef(.15,0,0)	
	Case 2 	; s
		glTranslatef(0,0,.15)
	Case 3	; w
		glTranslatef(-.15,0,0)
	End Select
	If envion<3
	drawmap(cmap)
	Else
	For m.map=Each map
	Select m\area
	Case 5 ;a1
	 drawmap(5)
	Case 6 ;b1
		glTranslatef(16*1.5,0,0)
		 drawmap(6)
	Case 7 ;c1
		glTranslatef(16*1.5,0,0)
		 drawmap(7)
	Case 8 ;d1
	glTranslatef(16*1.5,0,0)
		 drawmap(8)
		
	Case 9 ; a2
	glTranslatef(-3*(16*1.5),0,16*1.5)
		 drawmap(9)
	Case 10 ;b2
		glTranslatef(16*1.5,0,0)
		 drawmap(10)
	Case 11 ;c2
		glTranslatef(16*1.5,0,0)
		 drawmap(11)
	Case 12 ;a3
		glTranslatef(-2*(16*1.5),0,16*1.5)
		 drawmap(12)
	Case 13 ;b3
		glTranslatef(16*1.5,0,0)
		 drawmap(13)
	Case 14 ;c3
		glTranslatef(16*1.5,0,0)
		 drawmap(14)
	Case 15 ;a4
		glTranslatef(-2*(16*1.5),0,16*1.5)
		 drawmap(15)
	Case 16 ;b4
		glTranslatef(16*1.5,0,0)
		 drawmap(16)
	Case 33 ;e1
		glTranslatef((3*16*1.5),0,(-3*16*1.5))
		drawmap(33)
	Case 34 ;d2
		glTranslatef(-(16*1.5),0,(16*1.5))
		drawmap(34)
	Case 35 ;e2
		glTranslatef(16*1.5,0,0)
		drawmap(35)
	Case 36 ;d3
	glTranslatef(-(16*1.5),0,(16*1.5))
	drawmap(36)
	Case 37 ;e3
	glTranslatef(16*1.5,0,0)
	drawmap(37)
	Case 38 ;c4
	glTranslatef(-(2*16*1.5),0,(16*1.5))
	drawmap(38)
	Case 39 ;d4
	glTranslatef(16*1.5,0,0)
	drawmap(39)
	Case 40 ;e4
	glTranslatef(16*1.5,0,0)
	whereami()
	drawmap(40)
	End Select

	Next
	EndIf

	statusbar()
End Function

Function whereami()
lcmap=cmap
X=Int(Floor(camera\PX/1.50))
Y=Int(Floor(-(camera\Pz/1.50)))
If  Y>-1 And Y<16
	If X>-1 And X<16 
		cmap=5 : oarea=0
	Else If X>15 And X<32 
		cmap=6 : oarea=1
	Else If X>31 And X<48 
		cmap=7 : oarea=2
	Else If X>47 And X<64 
		cmap=8 : oarea=3
	Else If X>63 And X<80 
		cmap=33 : oarea=4
	EndIf
Else If Y<0 And Y>-17
	If X>-1 And X<16 
	cmap=9 : oarea=5
	Else If X>15 And X<32 
	cmap=10 : oarea=6
	Else If X>31 And X<48 
	cmap=11: oarea=7
	Else If X>47 And X<64 
	cmap=34 : oarea=8
	Else If X>63 And X<80 
	cmap=35 : oarea=9
	EndIf
Else If Y<-16 And Y>-33
	If X>-1 And X<16 
	cmap=12 : oarea=10
	Else If X>15 And X<32 
	cmap=13 : oarea=11
	Else If X>31 And X<48 
	cmap=14: oarea=12
	Else If X>47 And X<64 
	cmap=36 : oarea=13
	Else If X>63 And X<80 
	cmap=37 : oarea=14
	EndIf
Else If Y<-32 And Y>-65
	If X>-1 And X<16 
	cmap=15 : oarea=15
	Else If X>15 And X<32 
	cmap=16 : oarea=16
	Else If X>31 And X<48 
	cmap=38: oarea=17
	Else If X>47 And X<64 
	cmap=39 : oarea=18
	Else If X>63 And X<80 
	cmap=40 : oarea=19
	EndIf
EndIf
If cmap<>lcmap
	fillcollision(cmap)
	fillevent(cmap)
EndIf
End Function
Function statusbar()
	glViewport(0, 0, winwid-13,winhgt-30)
	glMatrixMode (GL_PROJECTION);								; Select The Projection Matrix
	glLoadIdentity ();											; Reset The Projection Matrix	
	;glOrtho(0.0, winwid-130,130,0.0,-1,1);
	glOrtho(0.0, 640,480,0.0,-1,1);
	
	glMatrixMode (GL_MODELVIEW);									// Select The Modelview Matrix
		glLoadIdentity ();												// Reset The Modelview Matrix

		glClear (GL_DEPTH_BUFFER_BIT)
	glLineWidth(4.0)
	glColor3f(1.0,0,0)

	glBegin(GL_LINES)
		glVertex2i(9,7)
		glVertex2i(635,7)	
		glVertex2i(10,330)
		glVertex2i(635,330)				
		glVertex2i(10,365)
		glVertex2i(635,365)				
		
		glVertex2i(10,6)	
		glVertex2i(10,475)
		glVertex2i(9,475)
		glVertex2i(635,475)			
		glVertex2i(500,6)	
		glVertex2i(500,330)	

		glVertex2i(635,6)	
		glVertex2i(635,475)	
	glEnd()
	drawminimap()
	If KeyDown(50)
		drawfullmap()
	EndIf
	mx=Int(Floor(camera\PX/1.50))-areamodX(oarea)
	my=Int(Floor(-(camera\Pz/1.50)))+areamodY(oarea)
	glEnable(GL_BLEND)
			glColor4f(1,1,1,1)
		glBindTexture(GL_TEXTURE_2D,font)
		glEnable(GL_TEXTURE_2D)
		glLoadIdentity();	

		For m.map=Each map
		If m\area=cmap
		mprint 10,335,m\name$+" X:"+mX+"/"+Int(Floor(camera\PX/1.50))+" Y:"+mY+"/"+Int(Floor(-(camera\Pz/1.50))),1
		EndIf
		Next

	glScalef(2,2,20)
		; drawbillboard(1)
;		If checkevent(Int(Floor(camera\PX/1.50))-areamodX(oarea),Int(Floor(-(camera\Pz/1.50)))+areamodY(oarea))
		If checkevent(mx,my)

		
		glColor4f(0.5,0.5,0.5,1)
		mPrint(201,51,"Event",0)
		glColor4f(1,1,1,1)
		mPrint(200,50,"Event",0)
		;glDisable(GL_TEXTURE_2D)
		EndIf
		;mprint 10,335,"'P' Protect",0
		;mprint 210,335,"Day= "+day,0
		;mprint 310,335,"Year= "+year,0
		mprint 510,335,"Face= "+drch$(direction),0
		glDisable(GL_BLEND)

		glColor4f(1.0,1.0,1.0,1.0)
End Function
	
Function drawbillboard(ID)
	;glEnable(GL_TEXTURE_2D)
	
	
	glEnable(GL_BLEND)
	;glBindTexture(GL_TEXTURE_2D,texture[ID])
	glColor4f(1.0,1.0,1.0,.2)
	glEnable(GL_ALPHA)
	glBegin(GL_QUADS)
		glTexCoord2f(0.0, 0.0);
		glVertex2i(160,80)
		glTexCoord2f(0.0, 1.0);
		glVertex2i(160,300)
		glTexCoord2f(1.0, 1.0);		
		glVertex2i(370,300)
		glTexCoord2f(1.0, 0.0);
		glVertex2i(370,80)
	glEnd
	glDisable(GL_BLEND)
End Function

Function drawmap(ID)
;glTranslatef(Rnd#(-1,1),0,0)
glEnable(GL_BLEND)
glEnable(GL_ALPHA_TEST)
glEnable(GL_TEXTURE_2D)
	For m.map=Each map
		If m\area=id

		glEnableClientState(GL_VERTEX_ARRAY)
		glEnableClientState(GL_TEXTURE_COORD_ARRAY)
		glTexCoordPointer(2,GL_FLOAT,0,uvarray)
		glVertexPointer(3,GL_FLOAT,0,vertexarray)
		;glColor4f(.2,.2,.2,.5)
		;glDrawElements(GL_QUADS,4,GL_UNSIGNED_INT,bob)
		;glColor4f(0.8,0.8,1.0,0.5)
		glBindTexture(GL_TEXTURE_2D,texture[(m\envion*3)+0])
		glDrawElements(GL_QUADS,BankSize(m\tris[0])/4,GL_UNSIGNED_INT,m\tris[0])
	;	glColor4f(0.7,0.7,1.0,0.5)
		glBindTexture(GL_TEXTURE_2D,texture[(m\envion*3)+1])
		glDrawElements(GL_QUADS,BankSize(m\tris[1])/4,GL_UNSIGNED_INT,m\tris[1])		
	;	glColor4f(0.75,0.75,1.0,0.5)		
		glBindTexture(GL_TEXTURE_2D,texture[(m\envion*3)+2])
		glDrawElements(GL_QUADS,BankSize(m\tris[2])/4,GL_UNSIGNED_INT,m\tris[2])
		glBindTexture(GL_TEXTURE_2D,texture[13])

		glTexCoordPointer(2,GL_FLOAT,0,uvarray2)
		glColor4f(1.0,1.0,0.5,0.1)
		Select m\envion
		Case 0		
		glColor4f(0.7,0.9,1.0,1.0)
		glDrawElements(GL_QUADS,BankSize(m\roof)/4,GL_UNSIGNED_INT,m\roof)
		glColor4f(1.0,0.6,0.5,1.0)		
		glDrawElements(GL_QUADS,BankSize(m\infloor)/4,GL_UNSIGNED_INT,m\infloor)
		Case 1
		glColor4f(0.3,0.4,0.5,1.0)
		glDrawElements(GL_QUADS,BankSize(m\roof)/4,GL_UNSIGNED_INT,m\roof)
		glColor4f(0.5,0.3,0.25,1.0)		
		glDrawElements(GL_QUADS,BankSize(m\infloor)/4,GL_UNSIGNED_INT,m\infloor)		
		Case 2
		glColor4f(1.0,0.9,1.0,1.0)
		glDrawElements(GL_QUADS,BankSize(m\roof)/4,GL_UNSIGNED_INT,m\roof)
		glColor4f(1.0,1.0,1.0,1.0)		
		glDrawElements(GL_QUADS,BankSize(m\infloor)/4,GL_UNSIGNED_INT,m\infloor)				
		Case 3
		
		glColor4f(1.0,1.0,1.0,1.0)		
		For n=0 To 6
		glBindTexture(GL_TEXTURE_2D,texture[13+n])
		glDrawElements(GL_QUADS,BankSize(m\outfloor[n])/4,GL_UNSIGNED_INT,m\outfloor[n])				
		Next
		End Select

		EndIf
	Next
		glDisable(GL_TEXTURE_2D)
		glColor4f(1.0,1.0,1.0,1.0)	
End Function

Function drawminimap()
	For m.map =Each map
	If m\area=cmap
	glColor4f(0.6,0.6,1,1)

	glBegin(GL_QUADS)
		glVertex2i(507,20)
		glVertex2i(627,20)
		glVertex2i(627,140)	
		glVertex2i(507,140)	

	glEnd()
	glColor4f(1.0,0.9,0.0,1.0)
	glBegin(GL_LINE_LOOP)
		glVertex2i(507,20)
		glVertex2i(627,20)
		glVertex2i(627,140)	
		glVertex2i(507,140)	
	glEnd()
	glLineWidth(2)
	glColor4f(1,1,1,1)
	For x=0 To 4
	For y=0 To 4
	glBegin(GL_LINES)
	x2=(x*24)+507
	y2=(y*24)+20	
		;glVertex2i(x2,y2)
		;glVertex2i(x2,y2+20)
	;	glVertex2i(x2+24,y2+24)	
	;	glVertex2i(x2+24,y2)	
	;	glVertex2i(x2,y2+24)	
	;	glVertex2i(x2+24,y2+24)
		glEnd()
	Next
	Next
	CX=Int(Floor(camera\PX/1.50))-areamodX(oarea)
	cY=Int(Floor(-(camera\Pz/1.50)))+areamodY(oarea)
x3=0:y3=0

	For y=cy+2 To cy-2 Step -1
	x3=0
	For x=cx-2 To cx+2 Step 1
	If y<0 Or x<0 Or x>15 Or y>15
	Else
	a=(y*16)+x
	cwall$=Right(Bin(PeekByte(m\mapdata,a)),8)
	;cwall$=Right(Bin(PeekByte(m\collmap,a)),8)
	
	If envion<3
	For d=0 To 3
	temp=getwall(cwall,d)
	x2=(x3*24)+508
	y2=(y3*24)+21
	glColor4f(0,0,0,1)
	drawwall(d,getwall(cwall,d),x2,y2)

	x2=(x3*24)+507
	y2=(y3*24)+20
	glColor4f(1,1,1,1)
	drawwall(d,getwall(cwall,d),x2,y2)
	Next
	Else
		glEnable(GL_TEXTURE_2D)
		temp=Int(Floor(GetOWall(cwall,0)))
					If temp=1 Or temp=3 Or temp=5 Or temp=7 Or temp=9 Or temp=11 Or temp=13 Or temp=15
				
					wll#=GetOWall(cwall,1)
					Select Int(Floor(wll))
					Case 0
				mwll=2
					Case 1
				mwll=1
					Case 2
				mwll=4
					Case 3
				mwll=5
					Case 4
				mwll=6
					Case 5
				mwll=3					
					Case 6
				mwll=7					
					Case 7
				mwll=10					
					Case 8
				mwll=9					
					Case 9
				mwll=11					
					Case 10
				mwll=12					
					Case 11
				mwll=20					
					Case 12
				mwll=16				
					Case 13
				mwll=18					
					Case 14
				mwll=19
					Case 15								
				mwll=19					
					End Select
					
					Else
					wll#=GetOWall(cwall,1)
					Select Int(Floor(wll))
					Case 0
				mwll=20
					Case 1
				mwll=21
					Case 2
				mwll=22
					Case 3
				mwll=23
					Case 4
				mwll=24
					Case 5
				mwll=25					
					Case 6
				mwll=26					
					Case 7
				mwll=27					
					Case 8
				mwll=28					
					Case 9
				mwll=15					
					Case 10
				mwll=17					
					Case 11
				mwll=29					
					Case 12
				mwll=13					
					Case 13
				mwll=14					
					Case 14
				mwll=21
					Case 15								
				mwll=30					
					End Select
					EndIf
;					DebugLog wll
					glBindTexture(GL_TEXTURE_2D,texminimap[mwll-1])
					x2=(x3*24)+507
					y2=(y3*24)+20
					
					glBegin(GL_QUADS)
					glTexCoord2i(0,0):glVertex2i(x2,y2)
					glTexCoord2i(1,0):glVertex2i(x2+24,y2)
					glTexCoord2i(1,-1):glVertex2i(x2+24,y2+24)
					glTexCoord2i(0,-1):glVertex2i(x2,y2+24)
					glEnd()
					glDisable(GL_TEXTURE_2D)

	EndIf
	EndIf
;	a=a+1
	x3=x3+1
	Next
	y3=y3+1
	Next

glColor4f(0,0,0,1)
	
	x=2: y=2
		x2=(x*24)+508
	y2=(y*24)+21	
	drawarrow(x2,y2)

glColor4f(1,1,1,1)

		x2=(x*24)+507
	y2=(y*24)+20
	drawarrow(x2,y2)
	


	Return 1
EndIf
	
	Next
	Return 0
End Function


Function drawarrow(x2,y2)
	glBegin(GL_LINE_LOOP)
	Select direction
	Case 0
		glVertex2i(x2+10,y2+20)
		glVertex2i(x2+14,y2+20)
		glVertex2i(x2+14,y2+12)
		glVertex2i(x2+20,y2+12)
		glVertex2i(x2+12,y2+5)
		glVertex2i(x2+4,y2+12)
		glVertex2i(x2+10,y2+12)
		glVertex2i(x2+10,y2+20)
	Case 1
		glVertex2i(x2+4,y2+10)
		glVertex2i(x2+4,y2+14)
		glVertex2i(x2+12,y2+14)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+19 ,y2+12)
		glVertex2i(x2+12 ,y2+4)
		glVertex2i(x2+12,y2+10)
		glVertex2i(x2+4,y2+10)
	Case 2
		glVertex2i(x2+10,y2+4)
		glVertex2i(x2+14,y2+4)
		glVertex2i(x2+14,y2+12)
		glVertex2i(x2+20,y2+12)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+4,y2+12)
		glVertex2i(x2+10,y2+12)
		glVertex2i(x2+10,y2+4)
	Case 3
		glVertex2i(x2+20,y2+10)
		glVertex2i(x2+20,y2+14)
		glVertex2i(x2+12,y2+14)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+5 ,y2+12)
		glVertex2i(x2+12 ,y2+4)
		glVertex2i(x2+12,y2+10)
		glVertex2i(x2+20,y2+10)
	End Select
	glEnd()
End Function
Function drawwall(dir,typ,x2,y2)
	Local door=False

	Select typ
	Case 0
		Return
	Case 1
;		glColor4f(1,1,1,1)
	Case 2
;		glColor4f(1,1,1,1)
		door=True
	Case 3
;		glColor4f(1,1,1,1)
	End Select
	glBegin(gl_lines)
Select dir
	Case 0
	glVertex2i(x2,y2)
	glVertex2i(x2+23,y2)
	If door=True
	glVertex2i(x2+5,y2)
	glVertex2i(x2+5,y2+5)
	glVertex2i(x2+5,y2+5)
	glVertex2i(x2+15,y2+5)
	glVertex2i(x2+15,y2+5)
	glVertex2i(x2+15,y2)
	End If
	Case 1
	glVertex2i(x2+23,y2+23)
	glVertex2i(x2+23,y2)
			If door=True
	glVertex2i(x2+23,y2+5)
	glVertex2i(x2+17,y2+5)
	glVertex2i(x2+17,y2+5)
	glVertex2i(x2+17,y2+15)
	glVertex2i(x2+17,y2+15)
	glVertex2i(x2+23,y2+15)
	End If
	Case 2
	glVertex2i(x2,y2+23)
	glVertex2i(x2+23,y2+23)
	If door=True
	glVertex2i(x2+5,y2+23)
	glVertex2i(x2+5,y2+17)
	glVertex2i(x2+5,y2+17)
	glVertex2i(x2+15,y2+17)
	glVertex2i(x2+15,y2+17)
	glVertex2i(x2+15,y2+23)
	End If
	Case 3
	If door=True
	glVertex2i(x2+0,y2+5)
	glVertex2i(x2+5,y2+5)
	glVertex2i(x2+5,y2+5)
	glVertex2i(x2+5,y2+15)
	glVertex2i(x2+5,y2+15)
	glVertex2i(x2+0,y2+15)
	End If
	glVertex2i(x2,y2)
	glVertex2i(x2,y2+23)
End Select
glEnd()
End Function


Function drawfullmap()
	For m.map =Each map
	If m\area=cmap
	glColor4f(0.6,0.6,1,1)

	CX=Int(Floor(camera\PX/1.50))
	cY=Int(Floor(-(camera\Pz/1.50)))
x3=0:y3=0

	;For y=cy+2 To cy-2 Step -1
	For y=15 To 0 Step -1
	x3=0
	For x=0 To 15
	;For x=cx-2 To cx+2 Step 1
	If y<0 Or x<0 Or x>15 Or y>15
	Else
	a=(y*16)+x
	cwall$=Right(Bin(PeekByte(m\mapdata,a)),8)
	x2=(x3*24)+20
	y2=(y3*24)+20	
	If m\envion<3
	For d=0 To 3
	temp=getwall(cwall,d)
	drawwall(d,getwall(cwall,d),x2,y2)
	Next
	Else
	glColor4f(1.0,1.0,1,1)

		glEnable(GL_TEXTURE_2D)
		temp=GetOWall(cwall,0)
					temp=Int(Floor(GetOWall(cwall,0)))
					If temp=1 Or temp=3 Or temp=5 Or temp=7 Or temp=9 Or temp=11 Or temp=13 Or temp=15
				
					wll#=GetOWall(cwall,1)
					Select Int(Floor(wll))
					Case 0
				mwll=2
					Case 1
				mwll=1
					Case 2
				mwll=4
					Case 3
				mwll=5
					Case 4
				mwll=6
					Case 5
				mwll=3					
					Case 6
				mwll=7					
					Case 7
				mwll=10					
					Case 8
				mwll=9					
					Case 9
				mwll=11					
					Case 10
				mwll=12					
					Case 11
				mwll=20					
					Case 12
				mwll=16				
					Case 13
				mwll=18					
					Case 14
				mwll=19
					Case 15								
				mwll=19					
					End Select
					
					Else
					wll#=GetOWall(cwall,1)
					Select Int(Floor(wll))
					Case 0
				mwll=20
					Case 1
				mwll=21
					Case 2
				mwll=22
					Case 3
				mwll=23
					Case 4
				mwll=24
					Case 5
				mwll=25					
					Case 6
				mwll=26					
					Case 7
				mwll=27					
					Case 8
				mwll=28					
					Case 9
				mwll=15					
					Case 10
				mwll=17					
					Case 11
				mwll=29					
					Case 12
				mwll=13					
					Case 13
				mwll=14					
					Case 14
				mwll=21
					Case 15								
				mwll=30					
					End Select
					EndIf
;					DebugLog wll
					glBindTexture(GL_TEXTURE_2D,texminimap[mwll-1])

					x2=(x3*24)+20
					y2=(y3*24)+20
					
					glBegin(GL_QUADS)
					glTexCoord2i(0,0):glVertex2i(x2,y2)
					glTexCoord2i(1,0):glVertex2i(x2+24,y2)
					glTexCoord2i(1,-1):glVertex2i(x2+24,y2+24)
					glTexCoord2i(0,-1):glVertex2i(x2,y2+24)
					glEnd()


	EndIf
	EndIf
;	a=a+1
	x3=x3+1
	Next
	y3=y3+1
	Next

	glBegin(GL_LINE_LOOP)
	x=2: y=2
	x2=(Cx*24)+20
	y2=360-(Cy*24)+20	

	Select direction
	Case 0
		glVertex2i(x2+10,y2+20)
		glVertex2i(x2+14,y2+20)
		glVertex2i(x2+14,y2+12)
		glVertex2i(x2+20,y2+12)
		glVertex2i(x2+12,y2+5)
		glVertex2i(x2+4,y2+12)
		glVertex2i(x2+10,y2+12)
		glVertex2i(x2+10,y2+20)
	Case 1
		glVertex2i(x2+4,y2+10)
		glVertex2i(x2+4,y2+14)
		glVertex2i(x2+12,y2+14)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+19 ,y2+12)
		glVertex2i(x2+12 ,y2+4)
		glVertex2i(x2+12,y2+10)
		glVertex2i(x2+4,y2+10)
	Case 2
		glVertex2i(x2+10,y2+4)
		glVertex2i(x2+14,y2+4)
		glVertex2i(x2+14,y2+12)
		glVertex2i(x2+20,y2+12)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+4,y2+12)
		glVertex2i(x2+10,y2+12)
		glVertex2i(x2+10,y2+4)
	Case 3
		glVertex2i(x2+20,y2+10)
		glVertex2i(x2+20,y2+14)
		glVertex2i(x2+12,y2+14)
		glVertex2i(x2+12,y2+20)
		glVertex2i(x2+5 ,y2+12)
		glVertex2i(x2+12 ,y2+4)
		glVertex2i(x2+12,y2+10)
		glVertex2i(x2+20,y2+10)
	End Select
	glEnd()
	Return 1
EndIf
	Next
	Return 0
End Function
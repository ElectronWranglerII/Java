/**
* 4 x 4 Matrix Class
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

import java.nio.FloatBuffer;

public class Matrix4 {
	private float 	M00, M10, M20, M30,
					M01, M11, M21, M31,
					M02, M12, M22, M32,
					M03, M13, M23, M33;
	
	public Matrix4() {
		this.identity();
	}
	
	//Creates a matrix using four Vector4
	public Matrix4(Vector4 M0, Vector4 M1, Vector4 M2, Vector4 M3) {
		this.M00 = M0.x();
		this.M01 = M0.y();
		this.M02 = M0.z();
		this.M03 = M0.w();
		
		this.M10 = M1.x();
		this.M11 = M1.y();
		this.M12 = M1.z();
		this.M13 = M1.w();
		
		this.M20 = M2.x();
		this.M21 = M2.y();
		this.M22 = M2.z();
		this.M23 = M2.w();
		
		this.M30 = M3.x();
		this.M31 = M3.y();
		this.M32 = M3.z();
		this.M33 = M3.w();
	}
	
	
	//Static methods
	/*
	 * Inverts the matrix
	 */
	public static Matrix4 invert(Matrix4 Matrix) {
		float Determinant = Matrix.determinant();
		if(Determinant != 0) {
			float InverseDeterminant = 1.0f / Determinant;

			float Temp00 =  Matrix3.determinant(Matrix.M11, Matrix.M12, Matrix.M13, Matrix.M21, Matrix.M22, Matrix.M23, Matrix.M31, Matrix.M32, Matrix.M33);
			float Temp01 = -Matrix3.determinant(Matrix.M10, Matrix.M12, Matrix.M13, Matrix.M20, Matrix.M22, Matrix.M23, Matrix.M30, Matrix.M32, Matrix.M33);
			float Temp02 =  Matrix3.determinant(Matrix.M10, Matrix.M11, Matrix.M13, Matrix.M20, Matrix.M21, Matrix.M23, Matrix.M30, Matrix.M31, Matrix.M33);
			float Temp03 = -Matrix3.determinant(Matrix.M10, Matrix.M11, Matrix.M12, Matrix.M20, Matrix.M21, Matrix.M22, Matrix.M30, Matrix.M31, Matrix.M32);

			float Temp10 = -Matrix3.determinant(Matrix.M01, Matrix.M02, Matrix.M03, Matrix.M21, Matrix.M22, Matrix.M23, Matrix.M31, Matrix.M32, Matrix.M33);
			float Temp11 =  Matrix3.determinant(Matrix.M00, Matrix.M02, Matrix.M03, Matrix.M20, Matrix.M22, Matrix.M23, Matrix.M30, Matrix.M32, Matrix.M33);
			float Temp12 = -Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M03, Matrix.M20, Matrix.M21, Matrix.M23, Matrix.M30, Matrix.M31, Matrix.M33);
			float Temp13 =  Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M02, Matrix.M20, Matrix.M21, Matrix.M22, Matrix.M30, Matrix.M31, Matrix.M32);

			float Temp20 =  Matrix3.determinant(Matrix.M01, Matrix.M02, Matrix.M03, Matrix.M11, Matrix.M12, Matrix.M13, Matrix.M31, Matrix.M32, Matrix.M33);
			float Temp21 = -Matrix3.determinant(Matrix.M00, Matrix.M02, Matrix.M03, Matrix.M10, Matrix.M12, Matrix.M13, Matrix.M30, Matrix.M32, Matrix.M33);
			float Temp22 =  Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M03, Matrix.M10, Matrix.M11, Matrix.M13, Matrix.M30, Matrix.M31, Matrix.M33);
			float Temp23 = -Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M02, Matrix.M10, Matrix.M11, Matrix.M12, Matrix.M30, Matrix.M31, Matrix.M32);

			float Temp30 = -Matrix3.determinant(Matrix.M01, Matrix.M02, Matrix.M03, Matrix.M11, Matrix.M12, Matrix.M13, Matrix.M21, Matrix.M22, Matrix.M23);
			float Temp31 =  Matrix3.determinant(Matrix.M00, Matrix.M02, Matrix.M03, Matrix.M10, Matrix.M12, Matrix.M13, Matrix.M20, Matrix.M22, Matrix.M23);
			float Temp32 = -Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M03, Matrix.M10, Matrix.M11, Matrix.M13, Matrix.M20, Matrix.M21, Matrix.M23);
			float Temp33 =  Matrix3.determinant(Matrix.M00, Matrix.M01, Matrix.M02, Matrix.M10, Matrix.M11, Matrix.M12, Matrix.M20, Matrix.M21, Matrix.M22);
			
			Vector4 M0 = new Vector4(Temp00 * InverseDeterminant, Temp10 * InverseDeterminant, Temp20 * InverseDeterminant, Temp30 * InverseDeterminant);
			Vector4 M1 = new Vector4(Temp01 * InverseDeterminant, Temp11 * InverseDeterminant, Temp21 * InverseDeterminant, Temp31 * InverseDeterminant);
			Vector4 M2 = new Vector4(Temp02 * InverseDeterminant, Temp12 * InverseDeterminant, Temp22 * InverseDeterminant, Temp32 * InverseDeterminant);
			Vector4 M3 = new Vector4(Temp03 * InverseDeterminant, Temp13 * InverseDeterminant, Temp23 * InverseDeterminant, Temp33 * InverseDeterminant);
			
			return new Matrix4(M0, M1, M2, M3);
		} else {
			return null;
		}
	}
	
	/*
	 * Multiplies the matrix
	 */
	public static Matrix4 multiply(Matrix4 A, Matrix4 B) {
		float M00 = A.M00 * B.M00 + A.M10 * B.M01 + A.M20 * B.M02 + A.M30 * B.M03;
		float M01 = A.M01 * B.M00 + A.M11 * B.M01 + A.M21 * B.M02 + A.M31 * B.M03;
		float M02 = A.M02 * B.M00 + A.M12 * B.M01 + A.M22 * B.M02 + A.M32 * B.M03;
		float M03 = A.M03 * B.M00 + A.M13 * B.M01 + A.M23 * B.M02 + A.M33 * B.M03;
		Vector4 M0 = new Vector4(M00, M01, M02, M03);
		
		float M10 = A.M00 * B.M10 + A.M10 * B.M11 + A.M20 * B.M12 + A.M30 * B.M13;
		float M11 = A.M01 * B.M10 + A.M11 * B.M11 + A.M21 * B.M12 + A.M31 * B.M13;
		float M12 = A.M02 * B.M10 + A.M12 * B.M11 + A.M22 * B.M12 + A.M32 * B.M13;
		float M13 = A.M03 * B.M10 + A.M13 * B.M11 + A.M23 * B.M12 + A.M33 * B.M13;
		Vector4 M1 = new Vector4(M10, M11, M12, M13);
		
		float M20 = A.M00 * B.M20 + A.M10 * B.M21 + A.M20 * B.M22 + A.M30 * B.M23;
		float M21 = A.M01 * B.M20 + A.M11 * B.M21 + A.M21 * B.M22 + A.M31 * B.M23;
		float M22 = A.M02 * B.M20 + A.M12 * B.M21 + A.M22 * B.M22 + A.M32 * B.M23;
		float M23 = A.M03 * B.M20 + A.M13 * B.M21 + A.M23 * B.M22 + A.M33 * B.M23;
		Vector4 M2 = new Vector4(M20, M21, M22, M23);
		
		float M30 = A.M00 * B.M30 + A.M10 * B.M31 + A.M20 * B.M32 + A.M30 * B.M33;
		float M31 = A.M01 * B.M30 + A.M11 * B.M31 + A.M21 * B.M32 + A.M31 * B.M33;
		float M32 = A.M02 * B.M30 + A.M12 * B.M31 + A.M22 * B.M32 + A.M32 * B.M33;
		float M33 = A.M03 * B.M30 + A.M13 * B.M31 + A.M23 * B.M32 + A.M33 * B.M33;
		Vector4 M3 = new Vector4(M30, M31, M32, M33);
		
		return new Matrix4(M0, M1, M2, M3);
	}
	
	/*
	 * Calculates a rotation matrix for the specified axis and angle
	 * and returns it as a new matrix
	 */
	public static Matrix4 rotation(Vector3 Axis, float Angle) {
		//Calculate common values
		float AngleCos = (float) Math.cos(Angle);						//rcos = cos(phi)
		float AngleSin = (float) Math.sin(Angle);						//rsin = sin(phi)
		float InverseAngleCos = 1.0f - AngleCos;						//1 - rcos
		float XSin = Axis.x() * AngleSin;								//u * rsin
		float YSin = Axis.y() * AngleSin;								//v * rsin
		float ZSin = Axis.z() * AngleSin;								//w * rsin
		float XY = Axis.x() * Axis.y();									//u * v
		float XZ = Axis.x() * Axis.z();									//u * w
		float YZ = Axis.y() * Axis.z();									//v * w
		//Calculate M0x
		float M00 = AngleCos + Axis.x() * Axis.x() * InverseAngleCos;	// rcos + u * u * (1 - rcos)
		float M01 =  ZSin + XY * InverseAngleCos;						// w * rsin + v * u * (1-rcos)
		float M02 = -YSin + XZ * InverseAngleCos;						//-v * rsin + w * u * (1 - rcos)
		Vector4 M0 = new Vector4(M00, M01, M02, 0.0f);
		//Calculate M1x
		float M10 = -ZSin + XY * InverseAngleCos;						//-w * rsin + u * v * (1 - rcos)
		float M11 = AngleCos + Axis.y() * Axis.y() * InverseAngleCos;	// rcos + v * v * (1 - rcos)
		float M12 =  XSin + YZ * InverseAngleCos;						// u * rsin + w * v * (1 - rcos)
		Vector4 M1 = new Vector4(M10, M11, M12, 0.0f);
		//Calculate M2x
		float M20 =  YSin + XZ * InverseAngleCos;						// v * rsin + u * w * (1-rcos)
		float M21 = -XSin + YZ * InverseAngleCos;						//-u * rsin + v * w * (1 - rcos)
		float M22 = AngleCos + Axis.z() * Axis.z() *InverseAngleCos;	// rcos + w * w * (1 - rcos)
		Vector4 M2 = new Vector4(M20, M21, M22, 0.0f);
		//Calculate M3x
		Vector4 M3 = new Vector4(0.0f, 0.0f, 0.0f, 1.0f);
		//Create matrix and return
		return new Matrix4(M0, M1, M2, M3);
	}
	
	/*
	 * Computes a transformation matrix
	 */
	public static Matrix4 transformation(Vector3 Translation, Vector3 Angle, Vector3 Scale) {
		Matrix4 Matrix = new Matrix4();
		Matrix4 Rotation;
		Matrix.translate(Translation);
		
		Rotation = Matrix4.rotation(new Vector3(1.0f, 0.0f, 0.0f), Angle.x());
		Matrix.rotate(Rotation);
		Rotation = Matrix4.rotation(new Vector3(0.0f, 1.0f, 0.0f), Angle.x());
		Matrix.rotate(Rotation);
		Rotation = Matrix4.rotation(new Vector3(0.0f, 0.0f, 1.0f), Angle.x());
		Matrix.rotate(Rotation);
		
		Matrix.scale(Scale);
		
		return Matrix;
	}
	
	/*
	 * Returns a vector4 containing the entries for the specified column
	 */
	public Vector4 column(int Column) {
		if(Column >= 0 && Column < 3) {
			return new Vector4(this.M00, this.M01, this.M02, this.M03);
		}else {
			return new Vector4();
		}
	}
	
	/*
	 * Computes the matrix determinant
	 */
	public float determinant() {
		float D = 	this.M00 * ((this.M11 * this.M22 * this.M33 +
								this.M12 * this.M23 * this.M31 +
								this.M13 * this.M21 * this.M32) -
								this.M13 * this.M22 * this.M31 -
								this.M11 * this.M23 * this.M32 -
								this.M12 * this.M21 * this.M33) -
					this.M01 * ((this.M10 * this.M22 * this.M33 +
								this.M12 * this.M23 * this.M30 +
								this.M13 * this.M20 * this.M32) -
								this.M13 * this.M22 * this.M30 -
								this.M10 * this.M23 * this.M32 -
								this.M12 * this.M20 * this.M33) +
					this.M02 * ((this.M10 * this.M21 * this.M33 +
								this.M11 * this.M23 * this.M30 +
								this.M13 * this.M20 * this.M31) -
								this.M13 * this.M21 * this.M30 -
								this.M10 * this.M23 * this.M31 -
								this.M11 * this.M20 * this.M33) -
					this.M03 * ((this.M10 * this.M21 * this.M32 +
								this.M11 * this.M22 * this.M30 +
								this.M12 * this.M20 * this.M31) -
								this.M12 * this.M21 * this.M30 -
								this.M10 * this.M22 * this.M31 -
								this.M11 * this.M20 * this.M32);
		return D;
	}
	
	/*
	 * Sets the matrix contents to an identity matrix
	 * 1, 0, 0, 0
	 * 0, 1, 0, 0
	 * 0, 0, 1, 0
	 * 0, 0, 0, 1
	 */
	public void identity() {
		this.M00 = 1.0f;
		this.M01 = 0.0f;
		this.M02 = 0.0f;
		this.M03 = 0.0f;
		
		this.M10 = 0.0f;
		this.M11 = 1.0f;
		this.M12 = 0.0f;
		this.M13 = 0.0f;
		
		this.M20 = 0.0f;
		this.M21 = 0.0f;
		this.M22 = 1.0f;
		this.M23 = 0.0f;
		
		this.M30 = 0.0f;
		this.M31 = 0.0f;
		this.M32 = 0.0f;
		this.M33 = 1.0f;
	}	
	
	/*
	 * Multiplies the matrix
	 */
	public void multiply(Matrix4 Matrix) {
		float Temp00 = this.M00 * Matrix.M00 + this.M10 * Matrix.M01 + this.M20 * Matrix.M02 + this.M30 * Matrix.M03;
		float Temp01 = this.M01 * Matrix.M00 + this.M11 * Matrix.M01 + this.M21 * Matrix.M02 + this.M31 * Matrix.M03;
		float Temp02 = this.M02 * Matrix.M00 + this.M12 * Matrix.M01 + this.M22 * Matrix.M02 + this.M32 * Matrix.M03;
		float Temp03 = this.M03 * Matrix.M00 + this.M13 * Matrix.M01 + this.M23 * Matrix.M02 + this.M33 * Matrix.M03;
		
		float Temp10 = this.M00 * Matrix.M10 + this.M10 * Matrix.M11 + this.M20 * Matrix.M12 + this.M30 * Matrix.M13;
		float Temp11 = this.M01 * Matrix.M10 + this.M11 * Matrix.M11 + this.M21 * Matrix.M12 + this.M31 * Matrix.M13;
		float Temp12 = this.M02 * Matrix.M10 + this.M12 * Matrix.M11 + this.M22 * Matrix.M12 + this.M32 * Matrix.M13;
		float Temp13 = this.M03 * Matrix.M10 + this.M13 * Matrix.M11 + this.M23 * Matrix.M12 + this.M33 * Matrix.M13;
		
		float Temp20 = this.M00 * Matrix.M20 + this.M10 * Matrix.M21 + this.M20 * Matrix.M22 + this.M30 * Matrix.M23;
		float Temp21 = this.M01 * Matrix.M20 + this.M11 * Matrix.M21 + this.M21 * Matrix.M22 + this.M31 * Matrix.M23;
		float Temp22 = this.M02 * Matrix.M20 + this.M12 * Matrix.M21 + this.M22 * Matrix.M22 + this.M32 * Matrix.M23;
		float Temp23 = this.M03 * Matrix.M20 + this.M13 * Matrix.M21 + this.M23 * Matrix.M22 + this.M33 * Matrix.M23;
		
		float Temp30 = this.M00 * Matrix.M30 + this.M10 * Matrix.M31 + this.M20 * Matrix.M32 + this.M30 * Matrix.M33;
		float Temp31 = this.M01 * Matrix.M30 + this.M11 * Matrix.M31 + this.M21 * Matrix.M32 + this.M31 * Matrix.M33;
		float Temp32 = this.M02 * Matrix.M30 + this.M12 * Matrix.M31 + this.M22 * Matrix.M32 + this.M32 * Matrix.M33;
		float Temp33 = this.M03 * Matrix.M30 + this.M13 * Matrix.M31 + this.M23 * Matrix.M32 + this.M33 * Matrix.M33;
		
		this.M00 = Temp00;
		this.M01 = Temp01;
		this.M02 = Temp02;
		this.M03 = Temp03;
		
		this.M10 = Temp10;
		this.M11 = Temp11;
		this.M12 = Temp12;
		this.M13 = Temp13;
		
		this.M20 = Temp20;
		this.M21 = Temp21;
		this.M22 = Temp22;
		this.M23 = Temp23;
		
		this.M30 = Temp30;
		this.M31 = Temp31;
		this.M32 = Temp32;
		this.M33 = Temp33;
	}
	
	/*
	 * Performs a rotation of the matrix
	 */
	public void rotate(Matrix4 RotationMatrix) {
		this.multiply(RotationMatrix);
	}
	
	/*
	 * Performs a scaling on the matrix
	 */
	public void scale(Vector3 Vector) {
		this.M00 *= Vector.x();
		this.M01 *= Vector.x();
		this.M02 *= Vector.x();
		this.M03 *= Vector.x();
		
		this.M10 *= Vector.y();
		this.M11 *= Vector.y();
		this.M12 *= Vector.y();
		this.M13 *= Vector.y();
		
		this.M20 *= Vector.z();
		this.M21 *= Vector.z();
		this.M22 *= Vector.z();
		this.M23 *= Vector.z();
	}
	
	/*
	 * Copies the matrix to a float buffer in column-major order
	 */
	public void store(FloatBuffer Buffer) {
		Buffer.put(this.M00);
		Buffer.put(this.M01);
		Buffer.put(this.M02);
		Buffer.put(this.M03);
		
		Buffer.put(this.M10);
		Buffer.put(this.M11);
		Buffer.put(this.M12);
		Buffer.put(this.M13);
		
		Buffer.put(this.M20);
		Buffer.put(this.M21);
		Buffer.put(this.M22);
		Buffer.put(this.M23);
		
		Buffer.put(this.M30);
		Buffer.put(this.M31);
		Buffer.put(this.M32);
		Buffer.put(this.M33);
		
		return;
	}
	
	/*
	 * Returns a string representation of this matrix
	 */
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(M00).append(' ').append(M10).append(' ').append(M20).append(' ').append(M30).append('\n');
		buf.append(M01).append(' ').append(M11).append(' ').append(M21).append(' ').append(M31).append('\n');
		buf.append(M02).append(' ').append(M12).append(' ').append(M22).append(' ').append(M32).append('\n');
		buf.append(M03).append(' ').append(M13).append(' ').append(M23).append(' ').append(M33).append('\n');
		return buf.toString();
	}
	
	/*
	 * Calculates a transform and returns it in a Vector4
	 */
	public Vector4 transform(Vector4 Vector) {
		float X = this.M00 * Vector.x() + this.M10 * Vector.y() + this.M20 * Vector.z() + this.M30 * Vector.w();
		float Y = this.M01 * Vector.x() + this.M11 * Vector.y() + this.M21 * Vector.z() + this.M31 * Vector.w();
		float Z = this.M02 * Vector.x() + this.M12 * Vector.y() + this.M22 * Vector.z() + this.M32 * Vector.w();
		float W = this.M03 * Vector.x() + this.M13 * Vector.y() + this.M23 * Vector.z() + this.M33 * Vector.w();
		Vector.set(X, Y, Z, W);
		return Vector;
	}
	
	/*
	 * Performs a translation on the matrix
	 */
	public void translate(Vector3 Vector) {
		this.M30 += this.M00 * Vector.x() + this.M10 * Vector.y() + this.M20 * Vector.z();
		this.M31 += this.M01 * Vector.x() + this.M11 * Vector.y() + this.M21 * Vector.z();
		this.M32 += this.M02 * Vector.x() + this.M12 * Vector.y() + this.M22 * Vector.z();
		this.M33 += this.M03 * Vector.x() + this.M13 * Vector.y() + this.M23 * Vector.z();
	}
	
	/*
	 * Zeroizes the matrix
	 */
	public void zeroize() {
		this.M00 = 0.0f;
		this.M01 = 0.0f;
		this.M02 = 0.0f;
		this.M03 = 0.0f;
		
		this.M10 = 0.0f;
		this.M11 = 0.0f;
		this.M12 = 0.0f;
		this.M13 = 0.0f;
		
		this.M20 = 0.0f;
		this.M21 = 0.0f;
		this.M22 = 0.0f;
		this.M23 = 0.0f;
		
		this.M30 = 0.0f;
		this.M31 = 0.0f;
		this.M32 = 0.0f;
		this.M33 = 0.0f;
	}
}

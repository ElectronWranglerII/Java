/**
* 4D Vector Class
* Implements Salvador Dali's Corpus Hypercubus
* Copyright (C) DeRemee Systems, IXE Electronics LLC
* Portions copyright IXE Electronics LLC, Republic Robotics, FemtoLaunch, FemtoSat, FemtoTrack, Weland
* This work is made available under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
* To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/.
*/

package Math;

public class Vector4 {
	private float W;
	private float X;
	private float Y;
	private float Z;
	
	public Vector4() {
		this.W = 0.0f;
		this.X = 0.0f;
		this.Y = 0.0f;
		this.Z = 0.0f;
	}
	
	public Vector4(float X, float Y, float Z, float W) {
		this.W = W;
		this.X = X;
		this.Y = Y;
		this.Z = Z;
	}
	
	public Vector4(Vector4 Vector) {
		this.W = Vector.w();
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	public Vector4(Vector3 Vector, float W) {
		this.W = W;
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	public Vector4(Vector2 Vector, float Z, float W) {
		this.W = W;
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Z;
	}
	
	/*
	 * STATIC METHODS
	 * All computation is performed using these methods, and they all return
	 * a the result in a new Vector4 or float object.
	 */
	
	/**
	 * Adds the "right side" vector to the "left side" vector, returning the 
	 * result in a new vector
	 * @param A left-side vector
	 * @param B right-side vector
	 * @return a new vector
	 */
	public static Vector4 add(Vector4 A, Vector4 B) {
		float W, X, Y, Z;
		W = A.w() + B.w();
		X = A.x() + B.x();
		Y = A.y() + B.y();
		Z = A.z() + B.z();
		return new Vector4(X, Y, Z, W);
	}
	
	/**
	 * Computes the dot product between two vectors, returning the result as a float
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new float representing the dot product
	 */
	public static float dot(Vector4 A, Vector4 B) {
		return A.x() * B.x() + A.y() * B.y() + A.z() * B.z() + A.w() * B.w();
	}
	
	public static float length(Vector4 Vector) {
		return (float) Math.sqrt(Vector.x() * Vector.x() + Vector.y() * Vector.y() + Vector.z() * Vector.z() + Vector.w() * Vector.w());
	}
	
	/**
	 * Negates the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector4 negate(Vector4 Vector) {
		return new Vector4(-Vector.x(), -Vector.y(), -Vector.z(), -Vector.w());
	}
	
	/**
	 * Normalizes the vector, returning the result as a new vector
	 * @param Vector a vector
	 * @return a new vector
	 */
	public static Vector4 normalize(Vector4 Vector) {
		float W, X, Y, Z;
		float Length = (float) Math.sqrt(Vector.x() * Vector.x() +
										 Vector.y() * Vector.y() +
										 Vector.z() * Vector.z() +
										 Vector.w() * Vector.w());
		if(Length != 0.0f) {
			Length = 1.0f / Length;
			W = Vector.w() * Length;
			X = Vector.x() * Length;
			Y = Vector.y() * Length;
			Z = Vector.z() * Length;
		} else {
			W = 0.0f;
			X = 0.0f;
			Y = 0.0f;
			Z = 0.0f;
		}
		return new Vector4(X, Y, Z, W);
	}
	
	/**
	 * Scales the vector by another vector, returning the result in a new vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector representing the XY scale
	 * @return a new vector
	 */
	public static Vector4 scale(Vector4 A, Vector4 B) {
		float NW, NX, NY, NZ;
		NW = A.w() * B.w();
		NX = A.x() * B.x();
		NY = A.y() * B.y();
		NZ = A.z() * B.z();
		return new Vector4(NX, NY, NZ, NW);
	}
	
	/**
	 * Subtracts the "right side" vector from the :left side" vector, returning
	 * the result in a vew vector
	 * @param A the "left side" vector
	 * @param B the "right side" vector
	 * @return a new vector
	 */
	public static Vector4 subtract(Vector4 A, Vector4 B) {
		float W, X, Y, Z;
		W = A.w() - B.w();
		X = A.x() - B.x();
		Y = A.y() - B.y();
		Z = A.z() - B.z();
		return new Vector4(X, Y, Z, W);
	}
	
	/*
	 * INSTANCE METHODS
	 * All computations are forwarded from these methods to the static methods
	 */
	
	//Accessors
	
	public void copy(Vector4 Vector) {
		Vector.set(this.X, this.Y, this.Z, this.W);
	}
	
	/**
	 * Computes the dot product between this vector and another vector, returning
	 * the result as a float
	 * @param Vector a vector
	 * @return a float representing the dot product
	 */
	public float dot(Vector4 Vector) {
		return Vector4.dot(this, Vector);
	}
	
	/**
	 * Calculates the vector's length, returning the result as a float
	 * @return a float representing the length
	 */
	public float length() {
		return Vector4.length(this);
	}
	
	/**
	 * Returns a string containing the vector's contents
	 * Format: "(X, Y, Z)"
	 */
	public String toString() {
		return "(" + this.X + ", " + this.Y + ", " + this.Z + ", " + this.W + ")";
	}
	
	public float w() {
		return this.W;
	}
	
	public float x() {
		return this.X;
	}
	
	public float y() {
		return this.Y;
	}
	
	public float z() {
		return this.Z;
	}
	
	//Mutators
	
	/**
	 * Adds another vector to this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void add(Vector4 Vector) {
		this.set(Vector4.add(this, Vector));
	}
	
	/**
	 * Sets the vector's components to zero	
	 */
	public void clear() {
		this.W = 0.0f;
		this.X = 0.0f;
		this.Y = 0.0f;
		this.Z = 0.0f;
	}
	
	/**
	 * Negates this vector, overwriting the existing values
	 */
	public void negate() {
		this.set(Vector4.negate(this));
	}
	
	/**
	 * Normalizes this vector, overwriting the existing values
	 */
	public void normalize() {
		this.set(Vector4.normalize(this));
	}
	
	/**
	 * Scales this vector by another vector, overwriting the existing values
	 * @param Vector a vector representing the XY scale
	 */
	public void scale(Vector4 Vector) {
		this.set(Vector4.scale(this, Vector));
	}
	
	/**
	 * Sets new values for the vector
	 * @param X a float representing the X component
	 * @param Y a float representing the Y component
	 * @param Z a float representing the Z component
	 */
	public void set(float X, float Y, float Z, float W) {
		this.W = W;
		this.X = X;
		this.Y = Y;
		this.Z = X;
	}
	
	/**
	 * Sets new values for the vector
	 * @param Vector
	 */
	public void set(Vector4 Vector) {
		this.W = Vector.w();
		this.X = Vector.x();
		this.Y = Vector.y();
		this.Z = Vector.z();
	}
	
	/**
	 * Subtracts a vector from this vector, overwriting the existing values
	 * @param Vector a vector
	 */
	public void subtract(Vector4 Vector) {
		Vector4.subtract(this, Vector);
	}
	
	public void w(float W) {
		this.W = W;
	}
	
	public void x(float X) {
		this.X = X;
	}
	
	public void y(float Y) {
		this.Y = Y;
	}
	
	public void z(float Z) {
		this.Z = Z;
	}
}

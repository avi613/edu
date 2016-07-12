package avi.edu.particle;

public class ParticleMovement {
    public Particle movePositive(Particle particle) {
        return new Particle(particle.getPosition() + 1);
    }

    public Particle moveNegative(Particle particle) {
        return new Particle(particle.getPosition() - 1);
    }
}

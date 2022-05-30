
package com.cire.formula1.packets.models.data;

public class EventDataDetails {

    private FastestLap fastestLap;
    private Retirement retirement;
    private TeamMateInPits teamMateInPits;
    private RaceWinner raceWinner;
    private Penalty penalty;
    private SpeedTrap speedTrap;
    private StartLights startLights;
    private DriveThroughPenaltyServed driveThroughPenaltyServed;
    private StopGoPenaltyServed stopGoPenaltyServed;
    private Flashback flashback;
    private Buttons buttons;

    /**
     * @return Fastest lap
     */
    public FastestLap getFastestLap() {
        return fastestLap;
    }

    public void setFastestLap(FastestLap fastestLap) {
        this.fastestLap = fastestLap;
    }

    /**
     * @return Retirement
     */
    public Retirement getRetirement() {
        return retirement;
    }

    public void setRetirement(Retirement retirement) {
        this.retirement = retirement;
    }

    /**
     * @return Teammate in pits
     */
    public TeamMateInPits getTeamMateInPits() {
        return teamMateInPits;
    }

    public void setTeamMateInPits(TeamMateInPits teamMateInPits) {
        this.teamMateInPits = teamMateInPits;
    }

    /**
     * @return Race winner
     */
    public RaceWinner getRaceWinner() {
        return raceWinner;
    }

    public void setRaceWinner(RaceWinner raceWinner) {
        this.raceWinner = raceWinner;
    }

    /**
     * @return Penalty
     */
    public Penalty getPenalty() {
        return penalty;
    }

    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    /**
     * @return Speed trap
     */
    public SpeedTrap getSpeedTrap() {
        return speedTrap;
    }

    public void setSpeedTrap(SpeedTrap speedTrap) {
        this.speedTrap = speedTrap;
    }

    /**
     * @return Start Lights
     */
    public StartLights getStartLights() {
        return startLights;
    }

    public void setStartLights(StartLights startLights) {
        this.startLights = startLights;
    }

    /**
     * @return Drive Through Penalty Served
     */
    public DriveThroughPenaltyServed getDriveThroughPenaltyServed() {
        return driveThroughPenaltyServed;
    }

    public void setDriveThroughPenaltyServed(DriveThroughPenaltyServed driveThroughPenaltyServed) {
        this.driveThroughPenaltyServed = driveThroughPenaltyServed;
    }

    /**
     * @return Stop Go Penalty Served
     */
    public StopGoPenaltyServed getStopGoPenaltyServed() {
        return stopGoPenaltyServed;
    }

    public void setStopGoPenaltyServed(StopGoPenaltyServed stopGoPenaltyServed) {
        this.stopGoPenaltyServed = stopGoPenaltyServed;
    }
    /**
     * @return Flashback
     */
    public Flashback getFlashback() {
        return flashback;
    }

    public void setFlashback(Flashback flashback) {
        this.flashback = flashback;
    }

    /**
     * @return Button
     */
    public Buttons getButtons() {
        return buttons;
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "EventDataDetails[fastestLap=" + this.fastestLap +
                ",retirement=" + this.retirement +
                ",teamMateInPits=" + this.teamMateInPits +
                ",raceWinner=" + this.raceWinner +
                ",penalty=" + this.penalty +
                ",speedTrap=" + this.speedTrap +
                ",startLights=" + this.startLights +
                ",driveThroughPenaltyServed=" + this.driveThroughPenaltyServed +
                ",stopGoPenaltyServed=" + this.stopGoPenaltyServed +
                ",flashback=" + this.flashback +
                ",buttons=" + this.buttons +
                "]";
    }
}



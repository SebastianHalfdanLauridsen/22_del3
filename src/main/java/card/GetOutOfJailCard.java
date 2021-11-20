package card;
import player.Player;

/**
 * //TODO
 */
public class GetOutOfJailCard extends AbstractCard{
    getOutOfJailCount = 1;
}

//ser om spilleren har et get out of jail card. (mangler funktionen at skip en tur og brug af kortet)
//Den her skal være i sammenhæng med jail field, da den tjekker om spilleren har kortet eller ej.


if(getOutOfJailCount<1){
    System.out.println(you have no get out of jail card, your turn is skipped);
    }

else{
    System.out.println(you have a get out of jail card, you got out of jail);
    getOutOfJailCount = 0; //fjerner kortet fra spilleren (da den blev brugt)
    }
 */

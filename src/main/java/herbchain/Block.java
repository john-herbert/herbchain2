package herbchain;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce; // will increment this until we get the desired hash difficulty (number of '0''s at the start of the generated hash)
						// instead of '0's it could be a random value.
	public static int difficulty = 6;  // tried 8, it took for ever, with '5', can be a million or so iterations.

	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();

		this.hash = calculateHash(); //Making sure we do this after we set the other values.
									// After we calculate this, we will change it at the mining step
								
	}

	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				data
				);
		return calculatedhash;
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash(); // Will continue calculate this until we get a hash that matches a hash
									// with a matching start string of the '00000....' (length depending on the 'difficulty' variable.
			
									
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
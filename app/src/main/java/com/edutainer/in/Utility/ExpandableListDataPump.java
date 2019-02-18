package com.edutainer.in.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        ArrayList<String> iphones = new ArrayList<>();

        iphones.add(new String("Please note that Cashback does not get credited immediately into the wallet, " +
                "instead it take 24 hours to reflect the same into your TalkCharge Wallet. " +
                "In case you have not received the cashback even after 24 hours, " +
                "please confirm that you have followed the Terms & Conditions of the offer.\n\n" +
                "If you applied a valid coupon and still did not receive cashback please email us at care@talkcharge.com"));


        ArrayList<String> nexus = new ArrayList<>();
        nexus.add(new String("We initiate instant refund to your TalkCharge wallet as soon as your transaction fails. However, bank takes 3 to 10 working day to process refund.\n\nIf your bank refund is still pending after 10 days please email us at care@talkcharge.com"));

        ArrayList<String> windowPhones = new ArrayList<>();
        windowPhones.add(new String("We always assure that you are getting an appropriate cashback. In case your cashback amount is less than what you expected then please go through the terms and conditions once again.\n\nIf you are still not satisfy with the cashback please email us at care@talkcharge.com"));


        ArrayList<String> fourth = new ArrayList<>();
        fourth.add(new String("Sometimes the operator takes up to 2 to 4 hours to process your recharge. In case you haven't still get your recharge done we will refund your entire amount within 24 hours.\n\nIf your recharge is not done and refund is not received in 24 hours please email us at care@talkcharge.com"));

        ArrayList<String> fifth = new ArrayList<>();
        fifth.add(new String("Nothing to worry about sometimes the operator takes up to 2 to 4 hours to process your recharge. In case you haven't still get your recharge done we will refund your entire amount within 24 hours.\n\nIf your recharge is still coming pending and refund is not received in 24 hours please email us at care@talkcharge.com"));

        ArrayList<String> sixth = new ArrayList<>();

        sixth.add(new String("This happens either because of connectivity issue or technical issue with your bank. In such cases your transaction amount is refunded within 24 hours in TalkCharge wallet.However, bank takes 3 to 10 working day to process refund.\n\nIf your bank refund is still pending after 10 days please email us at care@talkcharge.com"));


        ArrayList<String> seventh = new ArrayList<>();
        seventh.add(new String("We request you to check your email box and message box once again. In case you still not find it there please email us at care@talkcharge.com"));


        ArrayList<String> eight = new ArrayList<>();
        eight.add(new String("After you shop via TalkCharge, wait for 72 hours for us to track your" +
                "transaction. If you don't receive any confirmation from us within this time, please email us at care@talkcharge.com"));

        expandableListDetail.put("I did not receive my cashback", iphones);
        expandableListDetail.put("Did not get my refund", nexus);
        expandableListDetail.put("Unhappy with my cashback", windowPhones);
        expandableListDetail.put("Did not get recharge done", fourth);
        expandableListDetail.put("My recharge is pending", fifth);
        expandableListDetail.put("Payment failed but money deducted", sixth);
        expandableListDetail.put("Not received my Giftcard", seventh);
        expandableListDetail.put("Shopped via TalkCharge but not received cashback", eight);
        return expandableListDetail;
    }
}

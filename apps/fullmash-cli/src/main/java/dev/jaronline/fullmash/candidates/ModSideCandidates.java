package dev.jaronline.fullmash.candidates;

import dev.jaronline.fullmash.data.Mod;

import java.util.ArrayList;
import java.util.Arrays;

public class ModSideCandidates extends ArrayList<String> {
    ModSideCandidates() {
        super(Arrays.asList(Mod.Side.stringValues()));
    }
}

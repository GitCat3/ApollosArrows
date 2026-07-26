from PIL import Image
import os

headfiles = [f.name for f in os.scandir("texture_bases/heads")]
stickfiles = [f.name for f in os.scandir("texture_bases/sticks")]
featherfiles = [f.name for f in os.scandir("texture_bases/feathers")]

os.makedirs("output", exist_ok=True)

for headfile in headfiles:
    for stickfile in stickfiles:
        for featherfile in featherfiles:
            feather = Image.open(f"texture_bases/feathers/{featherfile}")
            stick = Image.open(f"texture_bases/sticks/{stickfile}")
            head = Image.open(f"texture_bases/heads/{headfile}")

            feather.paste(stick, (0, 0), stick)
            feather.paste(head, (0, 0), head)

            feather_name = featherfile.split(".")[0]
            stick_name = stickfile.split(".")[0]
            head_name = headfile.split(".")[0]
            feather.save(f"output/{feather_name}_{stick_name}_{head_name}.png")